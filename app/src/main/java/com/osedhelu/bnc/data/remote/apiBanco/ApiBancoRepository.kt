package com.osedhelu.bnc.data.remote.apiBanco

import androidx.lifecycle.LiveData
import com.osedhelu.bnc.data.local.database.Transaction
import com.osedhelu.bnc.data.local.database.TransactionDao
import com.osedhelu.bnc.data.remote.BancoDataSource
import com.osedhelu.bnc.data.remote.dto.AnnulmentDto
import com.osedhelu.bnc.data.remote.dto.AnnulmentRespDto
import com.osedhelu.bnc.data.remote.dto.PaymentDto
import com.osedhelu.bnc.data.remote.dto.PaymentRespDto
import com.osedhelu.bnc.data.remote.dto.ResponseDto
import com.osedhelu.bnc.utils.getDatetime
import com.osedhelu.bnc.utils.jsonParse
import com.osedhelu.bnc.utils.jsonStringify
import retrofit2.HttpException
import javax.inject.Inject


interface ApiBancoRepository {
    suspend fun emitPayment(body: PaymentDto): ResponseDto<PaymentRespDto>
    suspend fun AnnulmentPayment(body: AnnulmentDto): ResponseDto<AnnulmentRespDto>
    fun getAllTransationsDB(): LiveData<List<Transaction>>
    fun deleteInvoiceNotToday()
}

class ApiBancoRepositoryImp @Inject constructor(
    private val MiApiDataSource: BancoDataSource, private val transactionDb: TransactionDao
) : ApiBancoRepository {


    override suspend fun emitPayment(body: PaymentDto): ResponseDto<PaymentRespDto> {
        return try {

            val response = MiApiDataSource.emitPayment(body)

            val date = getDatetime()

            transactionDb.insert(
                Transaction(
                    receiptId = response.receiptId!!,
                    rrn = response.rrn!!,
                    statusCode = response.statusCode!!,
                    statusDescription = response.statusDescription!!,
                    amount = body.amount!!,
                    card = body.card!!,
                    ok = true,
                    fecha = date.fecha
                )
            )
            ResponseDto(
                data = response, ok = true
            )
        } catch (e: HttpException) {
            val response = e.response()?.errorBody()?.string()
            val date = getDatetime()
            if (response.isNullOrBlank()) {
                ResponseDto(
                    ok = false, data = PaymentRespDto()
                )
            } else {
                val resp = jsonParse<PaymentRespDto>(response)

                try {
                    transactionDb.insert(
                        Transaction(
                            receiptId = "${resp.receiptId}",
                            rrn = "${resp.rrn}",
                            statusCode = "${resp.statusCode}",
                            statusDescription = "${resp.statusDescription}",
                            amount = "${body.amount}",
                            card = "${body.card}",
                            ok = false,
                            fecha = date.fecha
                        )
                    )
                } catch (err: NullPointerException) {
                    println("xxxxxxxxxxxxxxxxxxxError NullPointerException ${err}")
                } catch (err: Exception) {
                    println("xxxxxxxxxxxxxxxxxxxError Exception ${err}")
                }
                ResponseDto(
                    ok = false, data = resp

                )
            }

        } catch (e: Exception) {
            println("xxxxxxxxxxxxxxxxxxxError Exception ${e}")
            ResponseDto(
                ok = false, data = PaymentRespDto()

            )
        }
    }

    override suspend fun AnnulmentPayment(
        body: AnnulmentDto,
    ): ResponseDto<AnnulmentRespDto> {
        return try {
            val resp = MiApiDataSource.AnnulmentPayment(body)
            this.transactionDb.deleteOneInvoice(body.receiptId)
            ResponseDto(
                ok = true, data = resp
            )

        } catch (e: HttpException) {

            val response = e.response()?.errorBody()?.string()

            val resp = response?.let { jsonParse<AnnulmentRespDto>(it) }
            ResponseDto(
                ok = false, data = resp!!
            )
        } catch (e: Exception) {
            ResponseDto(
                ok = false, data = AnnulmentRespDto("", "")
            )
        }
    }


    override fun getAllTransationsDB(): LiveData<List<Transaction>> {

        return this.transactionDb.getAll()
    }

    override fun deleteInvoiceNotToday() {
        val date = getDatetime()
        this.transactionDb.deleteInvoiceNotToday(date.fecha)
    }
}
