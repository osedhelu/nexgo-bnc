package com.disglobal.bnc.utils

import android.content.Context
import com.disglobal.bnc.nexgobnc
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.device.reader.RfCardTypeEnum
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import com.nexgo.oaf.apiv3.emv.EmvCardBrandEnum
import com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
import com.nexgo.oaf.apiv3.emv.PromptEnum
import java.text.SimpleDateFormat
import java.util.Date

data class EmvTransactionConfig(
    val amount: Long,             // Monto en centavos (ej: 1000 = $10.00)
    val currencyCode: String,     // Código ISO 4217 (ej: "840" USD)
    val merchantId: String,       // ID de comercio (proporcionado por el banco)
    val terminalId: String,       // ID de terminal (proporcionado por el banco)
    val transactionType: Byte = 0x00, // Tipo de transacción (00 = Venta)
    val countryCode: String = "840",    // Código de país ISO 3166-1 (ej: 840 = USA)
    val isContactless: Boolean = true
)

class EmvCardReader(ctx: Context) {
    private val deviceEngine = (ctx.applicationContext as nexgobnc).deviceEngine
    private val cardReader: CardReader = deviceEngine!!.cardReader
    private val emvHandler: EmvHandler2 =
        deviceEngine!!.getEmvHandler2("APP_NAME") // Reemplaza "APP_NAME"
    private lateinit var transConfig: EmvTransConfigurationEntity
//    fun setContactlessMode() {
//        emvHandler.contactlessConfigKernelId(
//            EmvCardBrandEnum.EMV_CARD_BRAND_MASTER,
//            0x01.toByte(),
//            byteArrayOf(0x01) // Kernel ID específico
//        )
//    }

    // region Helper Functions
    private fun isValidEMVCard(cardInfo: CardInfoEntity): Boolean {
        return cardInfo.cardExistslot == CardSlotTypeEnum.RF &&
                (cardInfo.rfCardType == RfCardTypeEnum.TYPE_A_CPU ||
                        cardInfo.rfCardType == RfCardTypeEnum.TYPE_B_CPU)
    }

    private fun setupTransactionConfig(config: EmvTransactionConfig) {
        transConfig = EmvTransConfigurationEntity().apply {
            transAmount = String.format("%012d", config.amount)
            currencyCode = config.currencyCode
            merId = config.merchantId
            termId = config.terminalId
            emvTransType = config.transactionType
            countryCode = config.countryCode
            emvEntryModeEnum = if (config.isContactless) {
                EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS
            } else {
                EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACT
            }
        }
    }

    // Iniciar transacción EMV con configuración externa
    fun startEmvTransaction(config: EmvTransactionConfig) {
        setupTransactionConfig(config)
        startCardDetection()
    }

    private fun String.mask(): String = replaceRange(0..11, "**** **** **** ")

    // Iniciar la detección de tarjeta EMV (contactless)
    fun startCardDetection() {
        val slots = hashSetOf(CardSlotTypeEnum.RF)
        // Timeout de 60 segundos, ajusta según necesidad
        val result = cardReader.searchCard(slots, 60, object : OnCardInfoListener {
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                when {
                    retCode == SdkResult.Success && cardInfo != null -> handleDetectedCard(cardInfo)
                    else -> handleFinalResult(retCode, null)
                }
            }

            override fun onSwipeIncorrect() = handleError("Swipe incorrecto")
            override fun onMultipleCards() = handleError("Múltiples tarjetas detectadas")
        })

        if (result != SdkResult.Success) handleError("Error al iniciar búsqueda: $result")
    }

    private fun handleDetectedCard(cardInfo: CardInfoEntity) {
        if (!isValidEMVCard(cardInfo)) {
            handleError("Tarjeta no soportada")
            return
        }
        // Verificar si es una tarjeta EMV
        if (cardInfo.cardExistslot === CardSlotTypeEnum.RF) {
            // Configurar parámetros EMV
            val emvResult = emvHandler.emvProcess(transConfig, object : OnEmvProcessListener2 {
                override fun onSelApp(
                    p0: MutableList<String>?, p1: MutableList<CandidateAppInfoEntity>?, p2: Boolean
                ) {
                    p0?.firstOrNull()?.let {
                        emvHandler.onSetSelAppResponse(0) // Seleccionar primera aplicación
                    }
                }

                override fun onTransInitBeforeGPO() {
                    emvHandler.onSetTransInitBeforeGPOResponse(true)
                }

                override fun onConfirmCardNo(p0: CardInfoEntity?) {
                    cardInfo.let {
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

    private fun handleError(message: String) {
        println(message)
        cardReader.stopSearch()
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