package com.osedhelu.bnc.data.remote


import com.osedhelu.bnc.config.PATH_BANK_ANNULATION
import com.osedhelu.bnc.config.PATH_BANK_AUTH
import com.osedhelu.bnc.data.remote.dto.AnnulmentDto
import com.osedhelu.bnc.data.remote.dto.AnnulmentRespDto
import com.osedhelu.bnc.data.remote.dto.PaymentDto
import com.osedhelu.bnc.data.remote.dto.PaymentRespDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface BancoDataSource {
    @Headers("${ApiServiceInterceptor.BACKEND_BANCO}:true")
    @POST(PATH_BANK_AUTH)
    suspend fun emitPayment(@Body body: PaymentDto): PaymentRespDto

    @Headers("${ApiServiceInterceptor.BACKEND_BANCO}:true")
    @POST(PATH_BANK_ANNULATION)
    suspend fun AnnulmentPayment(@Body body: AnnulmentDto): AnnulmentRespDto
}
