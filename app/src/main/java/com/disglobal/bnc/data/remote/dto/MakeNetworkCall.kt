package com.disglobal.bnc.data.remote.dto

import android.util.Log
import com.disglobal.bnc.utils.jsonStringify
import com.google.gson.JsonIOException
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
        Log.d("Error", response.toString())
        if (response.isNullOrBlank()) {
            ApiResponseStatus.Error(jsonStringify(ErrorMessage("Error")))
        } else {
            ApiResponseStatus.Error(response)
        }
    } catch (e: JsonIOException) {

        ApiResponseStatus.Error(e.message ?: "Error")
    } catch (e: Exception) {
        ApiResponseStatus.Error(e.message ?: "Error")
    }
}