package com.osedhelu.bnc.data.remote.dto

sealed class ApiResponseStatus<T>() {
    class Success<T>(val data: T) : ApiResponseStatus<T>()
    class Error<T>(val message: String) : ApiResponseStatus<T>()
    class Loading<T> : ApiResponseStatus<T>()
}
