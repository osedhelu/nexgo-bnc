package com.osedhelu.bnc.data.remote


import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

//    const val MI_API_HEADERDER = "BACKEDN_MIAPI"
object ApiServiceInterceptor : Interceptor {
    const val BACKEND_BANCO = "BACKEND_BANCO"

    private var BANCO_TOKENOAUTH2: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader("Accept", "*/*")
        requestBuilder.addHeader("Content-Type", "application/json")

        println("xxxxxxxxxxxxxURL: ${request.url()}    ===  ${this.BANCO_TOKENOAUTH2 !== null}")

        if (this.BANCO_TOKENOAUTH2 !== null) {
            requestBuilder.addHeader("Authorization", "Basic ${BANCO_TOKENOAUTH2}")
        }

        println("xxxxxxxxxxxxxURL: ${request.headers().get("")}    ===  Basic ${BANCO_TOKENOAUTH2}")
        return chain.proceed(requestBuilder.build())
    }


    fun setBankOAuth2Token(token: String) {
        this.BANCO_TOKENOAUTH2 = token
    }
}

fun createCustomOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(1420, TimeUnit.SECONDS)
        .readTimeout(1420, TimeUnit.SECONDS)
//        .sslSocketFactory(sslContext.socketFactory, trustManager)
        .addInterceptor(ApiServiceInterceptor)
        .hostnameVerifier { _, _ -> true }
        .build()
}