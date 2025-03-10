package com.disglobal.bnc.utils

import android.content.Context
import android.util.Log
import com.disglobal.bnc.nexgobnc
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.device.reader.RfCardTypeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
import com.nexgo.oaf.apiv3.emv.PromptEnum
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class EmvCardReaderNew(ctx: Context) {
    private val deviceEngine = (ctx.applicationContext as nexgobnc).deviceEngine
    private val cardReader: CardReader = deviceEngine!!.cardReader
    private val emvHandler: EmvHandler2 = deviceEngine!!.getEmvHandler2("APP_NAME")

    suspend fun startEmvTransaction(config: EmvTransactionConfig): Map<String, String> = 
        suspendCancellableCoroutine { continuation ->
            // Preparar configuración EMV
            val emvConfig = EmvTransConfigurationEntity().apply {
                transAmount = String.format("%012d", config.amount)
                currencyCode = config.currencyCode
                merId = config.merchantId
                termId = config.terminalId
                emvTransType = config.transactionType
                countryCode = config.countryCode
                emvProcessFlowEnum = EmvProcessFlowEnum.EMV_PROCESS_FLOW_FULL
            }

            // Slots para búsqueda de tarjeta
            val slots = hashSetOf(CardSlotTypeEnum.RF)

            // Iniciar búsqueda de tarjeta
            cardReader.searchCard(slots, 60, object : OnCardInfoListener {
                override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                    if (retCode == SdkResult.Success && cardInfo != null) {
                        // Validar tarjeta
                        if (isValidCard(cardInfo)) {
                            // Procesar transacción EMV
                            processEmvTransaction(emvConfig, cardInfo, continuation)
                        } else {
                            cardReader.stopSearch()
                            continuation.resumeWithException(Exception("Tarjeta no válida"))
                        }
                    } else {
                        cardReader.stopSearch()
                        continuation.resumeWithException(Exception("Error en detección de tarjeta: $retCode"))
                    }
                }

                override fun onSwipeIncorrect() {
                    cardReader.stopSearch()
                    continuation.resumeWithException(Exception("Swipe incorrecto"))
                }

                override fun onMultipleCards() {
                    cardReader.stopSearch()
                    continuation.resumeWithException(Exception("Múltiples tarjetas detectadas"))
                }
            })
        }

    private fun isValidCard(cardInfo: CardInfoEntity): Boolean {
        return cardInfo.cardExistslot == CardSlotTypeEnum.RF && 
               (cardInfo.rfCardType == RfCardTypeEnum.TYPE_A_CPU || 
                cardInfo.rfCardType == RfCardTypeEnum.TYPE_B_CPU)
    }

    private fun processEmvTransaction(
        emvConfig: EmvTransConfigurationEntity, 
        cardInfo: CardInfoEntity, 
        continuation: kotlinx.coroutines.CancellableContinuation<Map<String, String>>
    ) {
        val cardData = mutableMapOf<String, String>()

        // Extraer información de la tarjeta
        cardData["PAN"] = cardInfo.cardNo ?: ""
        cardData["Track2"] = cardInfo.tk2 ?: ""
        cardData["ExpirationDate"] = cardInfo.expiredDate ?: ""
        cardData["ServiceCode"] = cardInfo.serviceCode ?: ""
        cardData["MaskedCardNo"] = cardInfo.maskCardNo ?: ""

        // Iniciar proceso EMV
        val emvResult = emvHandler.emvProcess(emvConfig, object : OnEmvProcessListener2 {
            override fun onFinish(resultCode: Int, resultEntity: EmvProcessResultEntity?) {
                cardReader.stopSearch()
                
                if (resultCode == SdkResult.Success) {
                    cardData["EMVResultCode"] = resultCode.toString()
                    continuation.resume(cardData)
                } else {
                    continuation.resumeWithException(Exception("EMV Process Failed: $resultCode"))
                }
            }

            // Implementar métodos requeridos por OnEmvProcessListener2
            override fun onSelApp(p0: MutableList<String>?, p1: MutableList<com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity>?, p2: Boolean) {
                p0?.firstOrNull()?.let {
                    emvHandler.onSetSelAppResponse(0)
                }
            }

            override fun onTransInitBeforeGPO() {
                emvHandler.onSetTransInitBeforeGPOResponse(true)
            }

            override fun onConfirmCardNo(p0: CardInfoEntity?) {
                p0?.let {
                    emvHandler.onSetConfirmCardNoResponse(true)
                }
            }

            override fun onCardHolderInputPin(isOnlinePin: Boolean, retryTimes: Int) {
                // Manejar entrada de PIN si es necesario
                emvHandler.onSetPinInputResponse(true, null)
            }

            override fun onContactlessTapCardAgain() {
                emvHandler.onSetContactlessTapCardResponse(true)
            }

            override fun onOnlineProc() {
                emvHandler.onSetOnlineProcResponse(true, null)
            }

            override fun onPrompt(prompt: PromptEnum?) {
                // Manejar prompts si es necesario
                Log.d("EmvCardReader", "Prompt: $prompt")
            }

            override fun onRemoveCard() {
                emvHandler.onSetRemoveCardResponse()
            }
        })

        if (emvResult != SdkResult.Success) {
            cardReader.stopSearch()
            continuation.resumeWithException(Exception("EMV Process Initialization Failed: $emvResult"))
        }
    }

    fun shutdown() {
        cardReader.stopSearch()
    }
}