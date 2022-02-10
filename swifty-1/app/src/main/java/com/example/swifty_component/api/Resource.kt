package com.example.swifty_component.api

sealed class Resource<T>(
    val data: T? = null,
    val status: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(status: Int, data: T? = null) : Resource<T>(data, status)
    class Loading<T> : Resource<T>()
}