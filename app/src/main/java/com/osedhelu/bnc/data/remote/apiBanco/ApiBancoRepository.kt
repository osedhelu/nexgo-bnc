package com.osedhelu.bnc.data.remote.apiBanco

import com.osedhelu.bnc.data.local.database.TransactionDao
import com.osedhelu.bnc.data.remote.BancoDataSource
import com.osedhelu.bnc.data.remote.dto.ApiResponseStatus
import com.osedhelu.bnc.data.remote.dto.EchoTestDto
import com.osedhelu.bnc.data.remote.dto.EchoTestDtoResp
import com.osedhelu.bnc.data.remote.dto.GetInfoAffiliatesResp
import com.osedhelu.bnc.data.remote.dto.ReceiverStatusDTO
import com.osedhelu.bnc.data.remote.dto.TransacionDtoResp
import com.osedhelu.bnc.data.remote.dto.makeNetworkCall
import javax.inject.Inject


interface ApiBancoRepository {
    suspend fun receiverStatus(): ApiResponseStatus<ReceiverStatusDTO>
    suspend fun getInfoAffiliation(
        taxId: String,
        serial: String,
        appName: String,
        appVersion: String
    ): ApiResponseStatus<GetInfoAffiliatesResp>

    suspend fun getBatchSummary(affiliationId: String): ApiResponseStatus<String>
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

    suspend fun registerTransaction(body: String): ApiResponseStatus<String>
}

class ApiBancoRepositoryImp @Inject constructor(
    private val MiApiDataSource: BancoDataSource, private val transactionDb: TransactionDao
) : ApiBancoRepository {

    override suspend fun receiverStatus(): ApiResponseStatus<ReceiverStatusDTO> =
        makeNetworkCall {
            MiApiDataSource.receiverStatus()
        }

    override suspend fun getInfoAffiliation(
        taxId: String,
        serial: String,
        appName: String,
        appVersion: String
    ) = makeNetworkCall {
        MiApiDataSource.getInfoAffiliation(taxId, serial, appName, appVersion)
    }

    override suspend fun getBatchSummary(affiliationId: String): ApiResponseStatus<String> =
        makeNetworkCall {
            MiApiDataSource.getBatchSummary(affiliationId)
        }

    override suspend fun getInfoEchoTest(body: EchoTestDto): ApiResponseStatus<EchoTestDtoResp> =
        makeNetworkCall {
            MiApiDataSource.getInfoEchoTest(body)
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
            merchant, terminal, cant, all, batches, origin, lotNumber
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

    override suspend fun registerTransaction(body: String): ApiResponseStatus<String> =
        makeNetworkCall {
            MiApiDataSource.registerTransaction(body)
        }
}
