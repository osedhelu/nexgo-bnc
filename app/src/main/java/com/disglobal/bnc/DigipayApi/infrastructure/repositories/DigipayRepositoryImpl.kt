package com.disglobal.bnc.DigipayApi.infrastructure.repositories

import android.content.Context
import com.disglobal.bnc.DigipayApi.TlvBuilder
import com.disglobal.bnc.DigipayApi.domain.datasources.DigipayDatasource
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.DigipayApi.domain.entities.LotSummaryResponse
import com.disglobal.bnc.DigipayApi.domain.entities.ReceiverStatusDTO
import com.disglobal.bnc.DigipayApi.domain.entities.TransacionDtoResp
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.data.local.database.TransactionDao
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.data.remote.dto.makeNetworkCall
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class DigipayRepositoryImp @Inject constructor(
    private val MiApiDataSource: DigipayDatasource,
    private val transactionDb: TransactionDao,
    @ApplicationContext private val context: Context
) : DigipayRepository {
    // Datos de la última transacción
    private var lastTransactionTlv: String? = null

    override suspend fun receiverStatus(): ApiResponseStatus<ReceiverStatusDTO> = makeNetworkCall {
        MiApiDataSource.receiverStatus()
    }

    override suspend fun getInfoAffiliation(
        taxId: String,
        serial: String,
    ) = makeNetworkCall {
        MiApiDataSource.getInfoAffiliation(taxId, serial, "com.transactions.demo", "0.0.1-DEBUG")
    }

    override suspend fun getBatchSummary(affiliationId: String): ApiResponseStatus<LotSummaryResponse> =
        makeNetworkCall {
            MiApiDataSource.getBatchSummary(affiliationId)
        }

    override suspend fun getInfoEchoTest(body: EchoTestDto): ApiResponseStatus<EchoTestDtoResp> {
        return makeNetworkCall {
            MiApiDataSource.getInfoEchoTest(body)
        }
    }

    override suspend fun getTransactions(
        merchant: String,
        terminal: String,
        cant: Int,
        all: Boolean,
        batches: Boolean,
        origin: Boolean,
        lotNumber: String
    ): ApiResponseStatus<TransacionDtoResp> = makeNetworkCall {
        MiApiDataSource.getTransactions(
            merchantID = merchant,
            terminalID = terminal,
            cantidad = cant,
            all = all,
            batches = batches,
            origin = origin,
            lotNumber = lotNumber,
        )
    }

    override suspend fun getTransactionsAffiliation(
        affiliationId: String,
        cant: Int,
        all: Boolean,
        batches: Boolean,
        lotNumber: String,
        signature: Boolean
    ): ApiResponseStatus<Unit> = makeNetworkCall {
        MiApiDataSource.getTransactionsAffiliation(
            affiliationId, cant, all, batches, lotNumber, signature
        )
    }

    override suspend fun registerTransaction(requestBody: RequestBody): ApiResponseStatus<String> =
        makeNetworkCall {
            MiApiDataSource.processTransaction(requestBody)
        }

    override suspend fun processPayment(
        tlvData: String,
    ): ApiResponseStatus<String> = makeNetworkCall {
        // Crear el RequestBody con los datos TLV en formato hexadecimal
        val requestBody = tlvData.toRequestBody("application/octet-stream".toMediaTypeOrNull())

        // Guardar el TLV para posibles reversos o advice
        lastTransactionTlv = tlvData

        // Procesar la transacción
        MiApiDataSource.processTransaction(requestBody)
    }

    override suspend fun getAffiliateInfo(): GetInfoAffiliatesResp? {
        // Obtener la información del afiliado desde SharedPreferences
        return GetInfoAffiliatesResp.getCommerce(context)
    }

    suspend fun registerTransaction1(
        emvHandler2: EmvHandler2?,
        amount: String,
        invoiceNumber: String,
        terminalData: GetInfoAffiliatesResp
    ): ApiResponseStatus<String> = makeNetworkCall {

        // Construir el mensaje TLV para la venta
        val tlvMessage = TlvBuilder.buildSaleTlvMessage(
            emvHandler2 = emvHandler2,
            amount = amount,
            merchantId = (terminalData.commerceId ?: "").toString(),
            terminalId = (terminalData.deviceId ?: "").toString(),
            batchNumber = terminalData.lotNumber ?: "",
            stan = terminalData.stan ?: "",
            invoiceNumber = invoiceNumber,
            ksn = terminalData.ksn ?: ""
        )

        // Guardar el TLV para posibles reversos o advice
        lastTransactionTlv = tlvMessage

        // Enviar la transacción al servidor
        val requestBody = tlvMessage.toRequestBody("application/octet-stream".toMediaTypeOrNull())
        val response = MiApiDataSource.processTransaction(requestBody)
        response

    }
}