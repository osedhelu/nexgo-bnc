package com.disglobal.bnc.utils

import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
import com.nexgo.oaf.apiv3.emv.PromptEnum
import java.text.SimpleDateFormat
import java.util.Date


class EmvCardReader2(private val deviceEngine: DeviceEngine) {
    private val cardReader: CardReader = deviceEngine.cardReader
    private val emvHandler: EmvHandler2 =
        deviceEngine.getEmvHandler2("APP_NAME") // Reemplaza "APP_NAME"

    // Iniciar la detección de tarjeta EMV (contactless)
    fun startEmvCardDetection() {
        val slots = HashSet<CardSlotTypeEnum>()
        slots.add(CardSlotTypeEnum.RF) // Slot para tarjetas sin contacto

        // Timeout de 60 segundos, ajusta según necesidad
        val result = cardReader.searchCard(slots, 60, object : OnCardInfoListener {
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                if (retCode == SdkResult.Success && cardInfo != null) {
                    handleDetectedCard(cardInfo)
                } else {
                    // Manejar errores
                    println("Error o timeout: $retCode")
                }
            }

            override fun onSwipeIncorrect() {
                TODO("Not yet implemented")
            }

            override fun onMultipleCards() {
                TODO("Not yet implemented")
            }
        })

        if (result != SdkResult.Success) {
            println("Error al iniciar la búsqueda: $result")
        }
    }

    private fun handleDetectedCard(cardInfo: CardInfoEntity) {
        // Verificar si es una tarjeta EMV
        if (cardInfo.cardExistslot === CardSlotTypeEnum.RF) {
            // Configurar parámetros EMV
            val emvResult = emvHandler.emvProcess(EmvTransConfigurationEntity().apply {
                transAmount = "000000001000"   // 12 dígitos
                currencyCode = "840"           // ISO 4217
                transDate = SimpleDateFormat("yyMMdd").format(Date()) // Ej: "240610"
                transTime = SimpleDateFormat("HHmmss").format(Date()) // Ej: "153045"
                emvEntryModeEnum = EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS
                emvProcessFlowEnum = EmvProcessFlowEnum.EMV_PROCESS_FLOW_STANDARD
                merId = "123456789012345"      // ID de prueba
                termId = "12345678"            // ID de terminal de prueba
            }, object : OnEmvProcessListener2 {
                override fun onSelApp(
                    p0: MutableList<String>?,
                    p1: MutableList<CandidateAppInfoEntity>?,
                    p2: Boolean
                ) {
                    p0?.firstOrNull()?.let {
                        emvHandler.onSetSelAppResponse(0) // Seleccionar primera aplicación
                    }
                }

                override fun onTransInitBeforeGPO() {
                    emvHandler.onSetTransInitBeforeGPOResponse(true)
                }

                override fun onConfirmCardNo(p0: CardInfoEntity?) {
                    cardInfo?.let {
//                        showMessage("Tarjeta: ${it.cardNo?.mask()}") // Mostrar PAN enmascarado
                        emvHandler.onSetConfirmCardNoResponse(true)
                    }
                }

                override fun onCardHolderInputPin(p0: Boolean, p1: Int) {
                    showPinDialog(p0) // Implementar diálogo de PIN
                }

                override fun onContactlessTapCardAgain() {
//                    showMessage("Vuelva a acercar la tarjeta")
                    emvHandler.onSetContactlessTapCardResponse(true)
                }

                override fun onOnlineProc() {
                    TODO("Not yet implemented")
                }

                override fun onPrompt(prompt: PromptEnum?) {
                    prompt?.let {
                        println(
                            when (it) {
                                PromptEnum.OFFLINE_PIN_CORRECT -> "PIN Correct"
                                PromptEnum.OFFLINE_PIN_INCORRECT -> "Pin Incorrecto"
                                else -> it.toString()
                            }
                        )
                    }
                }

                override fun onRemoveCard() {
                    emvHandler.onSetRemoveCardResponse()
                }

                override fun onFinish(p0: Int, p1: EmvProcessResultEntity?) {
                    handleFinalResult(p0, p1)
                }

            })

            if (emvResult == SdkResult.Success) {
                // Transacción exitosa
                println("EMV OK! PAN: " + emvHandler.emvCardDataInfo)
            } else {
                println("Error EMV: $emvResult")
            }
        }

        // Detener la búsqueda después de operar
        cardReader.stopSearch()
    }

    private fun showPinDialog(isOnlinePin: Boolean) {
        // Implementar diálogo para entrada de PIN
    }

    // Método para cerrar recursos
    fun shutdown() {
        cardReader.stopSearch()
        cardReader.close(CardSlotTypeEnum.RF)
    }

    private var transactionListener: ((Int, p1: EmvProcessResultEntity?) -> Unit)? = null

    fun setTransactionResultListener(listener: (Int, p1: EmvProcessResultEntity?) -> Unit) {
        this.transactionListener = listener
    }

    private fun handleFinalResult(resultCode: Int, p1: EmvProcessResultEntity?) {
        transactionListener?.invoke(resultCode, p1)
    }
}