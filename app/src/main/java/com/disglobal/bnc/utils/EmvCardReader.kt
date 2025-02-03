package com.disglobal.bnc.utils

import android.content.Context
import com.disglobal.bnc.nexgobnc
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.device.reader.RfCardTypeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2


class EmvCardReader(ctx: Context) {
    val deviceEngine = (ctx.applicationContext as nexgobnc).deviceEngine

    private val cardReader: CardReader = deviceEngine!!.cardReader
    private val emvHandler: EmvHandler2 =
        deviceEngine!!.getEmvHandler2("APP_NAME") // Reemplaza "APP_NAME"

    // Iniciar la detección de tarjeta EMV (contactless)
    fun startEmvCardDetection() {
        val slots = HashSet<CardSlotTypeEnum>()
        slots.add(CardSlotTypeEnum.RF) // Slot para tarjetas sin contacto

        // Timeout de 60 segundos, ajusta según necesidad
        val result = cardReader.searchCard(slots, 60, object : OnCardInfoListener {
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                if (retCode == SdkResult.Success && cardInfo != null) {
                    handleEMVCard(cardInfo)
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

    private fun handleEMVCard(cardInfo: CardInfoEntity) {
        // Verificar si es tarjeta EMV (Contactless - RF)
        if (cardInfo.cardExistslot == CardSlotTypeEnum.RF && (cardInfo.rfCardType == RfCardTypeEnum.TYPE_A_CPU || cardInfo.rfCardType == RfCardTypeEnum.TYPE_B_CPU)) {
            // Validar datos básicos

            if (cardInfo.cardNo == null || cardInfo.expiredDate == null) {
                println("Datos de tarjeta incompletos")
                return
            }
            // ... (manejar resultado)
        } else {
            println("Tarjeta no EMV o tipo no soportado: " + cardInfo.rfCardType)
        }
        cardReader.stopSearch()
    }
    // Verificar si es una tarjeta EMV
}
//
