package com.example.swifty_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swifty_component.repository.Repository

class ApiViewModelProviderFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel(repository) as T
    }
}