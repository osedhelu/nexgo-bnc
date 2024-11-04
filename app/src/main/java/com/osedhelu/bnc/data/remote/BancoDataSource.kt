package com.osedhelu.bnc.data.remote

import com.osedhelu.bnc.config.BATCH_SUMMARY
import com.osedhelu.bnc.config.ECHO_TEST
import com.osedhelu.bnc.config.GET_INFO_AFFILIATION
import com.osedhelu.bnc.config.PATH_GET_TRANSACTION
import com.osedhelu.bnc.config.PATH_RECEIVER_STATUS
import com.osedhelu.bnc.config.PATH_TRANSACTION_AFFILIATION
import com.osedhelu.bnc.data.remote.dto.AnnulmentDto
import com.osedhelu.bnc.data.remote.dto.AnnulmentRespDto
import com.osedhelu.bnc.data.remote.dto.EchoTestDto
import com.osedhelu.bnc.data.remote.dto.EchoTestDtoResp
import com.osedhelu.bnc.data.remote.dto.GetInfoAffiliatesResp
import com.osedhelu.bnc.data.remote.dto.PaymentDto
import com.osedhelu.bnc.data.remote.dto.PaymentRespDto
import com.osedhelu.bnc.data.remote.dto.ReceiverStatusDTO
import com.osedhelu.bnc.data.remote.dto.TransacionDtoResp
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface BancoDataSource {
//    @Headers("${ApiServiceInterceptor.BACKEND_BANCO}:true")
//    @POST(PATH_BANK_AUTH)
//    suspend fun emitPayment(@Body body: PaymentDto): PaymentRespDto

//    @Headers("${ApiServiceInterceptor.BACKEND_BANCO}:true")
//    @POST(PATH_BANK_ANNULATION)
//    suspend fun AnnulmentPayment(@Body body: AnnulmentDto): AnnulmentRespDto


    @GET("$GET_INFO_AFFILIATION/:taxId/:serial")
    suspend fun getInfoAffiliation(
        @Path("taxId") taxId: String,
        @Path("serial") serial: String,
        @Query("appName") appName: String,
        @Query("appVersion") appVersion: String
    ): GetInfoAffiliatesResp

    @GET(BATCH_SUMMARY)
    suspend fun getBatchSummary(
        @Query("affiliationId") appName: String,
    ): String

    @POST(ECHO_TEST)
    suspend fun getInfoEchoTest(@Body body: EchoTestDto): EchoTestDtoResp

    @GET("$PATH_GET_TRANSACTION/{merchant}/{terminal}")
    suspend fun getTransactions(
        @Path("merchant") merchant: String,
        @Path("terminal") terminal: String,
        @Query("cant") cant: Int,
        @Query("all") all: Boolean,
        @Query("batches") batches: Boolean,
        @Query("origin") origin: Boolean,
        @Query("lotNumber") lotNumber: String
    ): TransacionDtoResp

    @GET("$PATH_TRANSACTION_AFFILIATION/{affiliationId}")
    suspend fun getTransactionsAffiliation(
        @Path("affiliationId") merchant: String,
        @Query("cant") cant: Int,
        @Query("all") all: Boolean,
        @Query("batches") batches: Boolean,
        @Query("lotNumber") lotNumber: String,
        @Query("signature") signature: Boolean
    )

    @Headers("Content-Type: text/plain;charset=UTF-8")
    @POST(PATH_GET_TRANSACTION)
    suspend fun registerTransaction(@Body body: String): String

    @GET(PATH_RECEIVER_STATUS)
    suspend fun receiverStatus(): ReceiverStatusDTO

}
