package com.osedhelu.bnc.data.remote.dto

import com.osedhelu.bnc.utils.jsonStringify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

data class ErrorMessage(
    val message: String
)

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: HttpException) {
        val response = e.response()?.errorBody()?.string()
        if (response.isNullOrBlank()) {
            ApiResponseStatus.Error(jsonStringify(ErrorMessage("Error")))
        } else {
            ApiResponseStatus.Error(response)
        }
    } catch (e: Exception) {
        ApiResponseStatus.Error(e.message ?: "Error")
    }
}