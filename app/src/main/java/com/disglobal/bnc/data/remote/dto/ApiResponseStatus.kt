package com.disglobal.bnc.data.remote.dto

sealed class ApiResponseStatus<T>() {
    class Success<T>(val data: T) : ApiResponseStatus<T>()
    class Error<T>(val message: String) : ApiResponseStatus<T>()
    class Loading<T>(val message: String = "Cargando...") : ApiResponseStatus<T>()
}
