package com.example.swifty_component.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.swifty_component.api.AccessToken
import com.example.swifty_component.api.RetrofitInstance
import com.example.swifty_component.utils.Constants
import retrofit2.Response

class Repository(val context: Context) {

    var accessToken : String? = null
    var code: String? = null
    var login: String? = null

    init {
        val sp = context.getSharedPreferences(Constants.SHARED_PREF_URL, MODE_PRIVATE)
        accessToken = sp.getString(Constants.ACCESS_TOKEN_KEY, null)
        code = sp.getString(Constants.CODE, null)
    }

    fun saveLogin(newLogin: String) { login = newLogin }

    suspend fun getToken(
        grantType: String,
        clientId: String,
        clientSecret: String,
        code: String
    ) : Response<AccessToken> {
        val tokenResponse = RetrofitInstance.api.getToken(grantType, clientId, clientSecret, code)
        if(tokenResponse.isSuccessful) {
            saveAccessTokenInSharedPreferences(requireNotNull(tokenResponse.body()))
            saveCode(code)
        }
        return tokenResponse
    }

    suspend fun getLimitedUserInfoByLogin() = RetrofitInstance.api.getLimitedUserInfoByLogin("Bearer $accessToken", login ?: "")

    suspend fun getCompleteUserInfoById(id: String) = RetrofitInstance.api.getCompleteUserInfoById("Bearer $accessToken", id)

    private fun saveAccessTokenInSharedPreferences(token : AccessToken) {
        val sp = context.getSharedPreferences(Constants.SHARED_PREF_URL, MODE_PRIVATE)
        sp.edit()
            .putString(Constants.ACCESS_TOKEN_KEY, token.accessToken)
            .apply()
    }

    private fun saveCode(code : String) {
        val sp = context.getSharedPreferences(Constants.SHARED_PREF_URL, MODE_PRIVATE)
        sp.edit()
            .putString(Constants.CODE, code)
            .apply()
    }
}