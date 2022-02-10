package com.example.swifty_component

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swifty_component.api.Resource
import com.example.swifty_component.models.User
import com.example.swifty_component.repository.Repository
import kotlinx.coroutines.launch

class ApiViewModel(
    private val repository: Repository,
) : ViewModel() {

    val responseQueue = mutableStateOf<Resource<User>>(Resource.Loading())

    fun checkIfTokenIsPresent() : Boolean {
        repository.accessToken?.let {
            return true
        }
        return false
    }

    fun getToken(
        grantType: String,
        clientId: String,
        clientSecret: String,
        code: String
    ) = viewModelScope.launch { repository.getToken(grantType, clientId, clientSecret, code) }

    fun saveLogin(login: String) { repository.saveLogin(login) }

    fun getUserById() {
        viewModelScope.launch {
            responseQueue.value = Resource.Loading()

            val limitedInfoResponse = repository.getLimitedUserInfoByLogin()
            if(!limitedInfoResponse.isSuccessful || limitedInfoResponse.body()?.size == 0)
                responseQueue.value = Resource.Error(limitedInfoResponse.code())
            else {
                val id = limitedInfoResponse.body()?.get(0)?.id.toString()
                val responseWithUser = repository.getCompleteUserInfoById(id)
                if(responseWithUser.isSuccessful) {
                    if(null == responseWithUser.body())
                        responseQueue.value = Resource.Error(404)
                    else
                        responseQueue.value = Resource.Success(
                            requireNotNull(responseWithUser.body())
                        )
                }
                else {
                    responseQueue.value = Resource.Error(responseWithUser.code())
                }
            }
        }
    }

}