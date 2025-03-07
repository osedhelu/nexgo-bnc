package com.disglobal.bnc.data.remote

import com.disglobal.bnc.config.BATCH_SUMMARY
import com.disglobal.bnc.config.ECHO_TEST
import com.disglobal.bnc.config.GET_INFO_AFFILIATION
import com.disglobal.bnc.config.PATH_GET_TRANSACTION
import com.disglobal.bnc.config.PATH_RECEIVER_STATUS
import com.disglobal.bnc.config.PATH_TRANSACTION_AFFILIATION
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.DigipayApi.domain.entities.LotSummaryResponse
import com.disglobal.bnc.DigipayApi.domain.entities.ReceiverStatusDTO
import com.disglobal.bnc.DigipayApi.domain.entities.TransacionDtoResp
import okhttp3.RequestBody
import okhttp3.ResponseBody
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


}
