package com.disglobal.bnc.DigipayApi.domain.repositories

import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.DigipayApi.domain.entities.LotSummaryResponse
import com.disglobal.bnc.DigipayApi.domain.entities.ReceiverStatusDTO
import com.disglobal.bnc.DigipayApi.domain.entities.TransacionDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.TransactionProcessResponse
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import okhttp3.RequestBody


interface DigipayRepository {
    suspend fun receiverStatus(): ApiResponseStatus<ReceiverStatusDTO>
    suspend fun getInfoAffiliation(
        taxId: String,
        serial: String,
    ): ApiResponseStatus<GetInfoAffiliatesResp>

    suspend fun getBatchSummary(affiliationId: String): ApiResponseStatus<LotSummaryResponse>
    suspend fun getInfoEchoTest(body: EchoTestDto): ApiResponseStatus<EchoTestDtoResp>
    suspend fun getTransactions(
        merchant: String,
        terminal: String,
        cant: Int,
        all: Boolean,
        batches: Boolean,
        origin: Boolean,
        lotNumber: String
    ): ApiResponseStatus<TransacionDtoResp>

    suspend fun getTransactionsAffiliation(
        affiliationId: String,
        cant: Int,
        all: Boolean,
        batches: Boolean,
        lotNumber: String,
        signature: Boolean
    ): ApiResponseStatus<Unit>

    suspend fun registerTransaction(
        requestBody: RequestBody
    ): ApiResponseStatus<String>

    /**
     * Procesa un pago con tarjeta
     * @param tlvData Datos TLV de la transacción
     * @return Respuesta del procesamiento de la transacción
     */
    suspend fun processPayment(
        tlvData: String,
    ): ApiResponseStatus<String>

    /**
     * Obtiene la información del afiliado/comercio
     * @return Información del afiliado/comercio
     */
    suspend fun getAffiliateInfo(): GetInfoAffiliatesResp?
}

