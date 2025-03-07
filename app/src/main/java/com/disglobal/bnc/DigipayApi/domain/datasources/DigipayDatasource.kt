package com.disglobal.bnc.DigipayApi.domain.datasources

import com.disglobal.bnc.DigipayApi.domain.entities.BatchSummaryResponse
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.DigipayApi.domain.entities.LotSummaryResponse
import com.disglobal.bnc.DigipayApi.domain.entities.ReceiverStatusDTO
import com.disglobal.bnc.DigipayApi.domain.entities.TerminalValidationResponse
import com.disglobal.bnc.DigipayApi.domain.entities.TransacionDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.TransactionProcessResponse
import com.disglobal.bnc.DigipayApi.domain.entities.TransactionResponse
import com.disglobal.bnc.config.BATCH_SUMMARY
import com.disglobal.bnc.config.ECHO_TEST
import com.disglobal.bnc.config.GET_INFO_AFFILIATION
import com.disglobal.bnc.config.PATH_GET_TRANSACTION
import com.disglobal.bnc.config.PATH_RECEIVER_STATUS
import com.disglobal.bnc.config.PATH_TRANSACTION_AFFILIATION
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz para la API de Digipay
 * Basada en la documentación proporcionada
 */
interface DigipayDatasource {

    /**
     * Validación de terminal
     * @param taxID RIF del comercio
     * @param serial Serial del dispositivo POS
     * @param ksn Key Serial Number (opcional)
     * @param appName Nombre de Aplicación (opcional)
     * @param appVersion Versión de Aplicación (opcional)
     */
    @GET("$GET_INFO_AFFILIATION/{taxID}/{serial}/")
    suspend fun getInfoAffiliation(
        @Path("taxID") taxID: String,
        @Path("serial") serial: String,
        @Query("ksn") ksn: String? = null,
        @Query("appName") appName: String? = null,
        @Query("appVersion") appVersion: String? = null
    ): GetInfoAffiliatesResp


    /**
     * Lista de transacciones
     * @param merchantID ID del Merchant
     * @param terminalID ID del terminal
     * @param origin Muestra firma (opcional)
     * @param all Retorna todos los tipos de recibos (opcional)
     * @param cantidad Retorna las últimas n del lote (opcional)
     * @param batches Retorna los cierres (opcional)
     * @param lotNumber Si está retorna el solicitado si no el último (opcional)
     */
    @GET("$PATH_GET_TRANSACTION/{merchantID}/{terminalID}")
    suspend fun getTransactions(
        @Path("merchantID") merchantID: String,
        @Path("terminalID") terminalID: String,
        @Query("origin") origin: Boolean? = null,
        @Query("all") all: Boolean? = null,
        @Query("cantidad") cantidad: Int? = null,
        @Query("batches") batches: Boolean? = null,
        @Query("lotNumber") lotNumber: String? = null
    ): TransacionDtoResp

    /**
     * Resumen de Lote
     * @param affiliationId DigipayID
     */
    @GET("$GET_INFO_AFFILIATION/transactions/")
    suspend fun getBatchSummary(
        @Query("affiliationId") affiliationId: String
    ): LotSummaryResponse

    /**
     * Venta, Reverso, Anulación y Advice
     * Envía un mensaje TLV en el body para procesar la transacción
     */
    @Headers(
        "Accept: */*",
        "Content-Type: text/plain"
    )
    @POST(PATH_GET_TRANSACTION)
    suspend fun processTransaction(
        @Body tlvData: RequestBody
    ): TransactionProcessResponse


    @POST(ECHO_TEST)
    suspend fun getInfoEchoTest(@Body body: EchoTestDto): EchoTestDtoResp


    @GET("$PATH_TRANSACTION_AFFILIATION/{affiliationId}")
    suspend fun getTransactionsAffiliation(
        @Path("affiliationId") merchant: String,
        @Query("cant") cant: Int,
        @Query("all") all: Boolean,
        @Query("batches") batches: Boolean,
        @Query("lotNumber") lotNumber: String,
        @Query("signature") signature: Boolean
    )


    @GET(PATH_RECEIVER_STATUS)
    suspend fun receiverStatus(): ReceiverStatusDTO

}
