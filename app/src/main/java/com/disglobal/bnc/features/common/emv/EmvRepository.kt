package com.disglobal.bnc.features.common.emv

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.disglobal.bnc.ui.test.EmvUtils
import com.nexgo.common.ByteUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.emv.EmvCardBrandEnum
import com.nexgo.oaf.apiv3.emv.EmvDataSourceEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvOnlineResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmvRepository @Inject constructor(
    private val deviceEngine: DeviceEngine, private val emvUtils: EmvUtils
) {
    private var emvHandler2: EmvHandler2? = null

    init {
        emvHandler2 = deviceEngine.getEmvHandler2("app2")
        emvHandler2?.emvDebugLog(true)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun initEmvAid(): Boolean {
        try {
            // Verificar si emvHandler2 está inicializado
            if (emvHandler2 == null) {
                Log.e("nexgo", "Error: EmvHandler2 no está inicializado")
                return false
            }

            // Eliminar todos los AID existentes
            emvHandler2?.delAllAid()

            // Obtener lista de AID desde EmvUtils
            val aidEntityList = EmvUtils.aidList

            // Verificar si la lista de AID está vacía o nula
            if (aidEntityList == null || aidEntityList.isEmpty()) {
                Log.e("nexgo", "Error: Lista de AID está vacía o nula")
                return false
            }

            // Registrar detalles de cada AID
            aidEntityList.forEachIndexed { index, aidEntity ->
                Log.d("nexgo", "AID $index: ${aidEntity.aid}")
                Log.d("nexgo", "  - Versión de aplicación: ${aidEntity.appVerNum}")
                Log.d("nexgo", "  - Límite de transacción: ${aidEntity.transLimit}")
            }

            // Intentar agregar AID uno por uno
            var successCount = 0
            var failureCount = 0

            // Mapeo de AID a marcas de tarjeta
            val aidToBrandMap = mapOf(
                "A0000000031010" to EmvCardBrandEnum.EMV_CARD_BRAND_VISA,
                "A0000000041010" to EmvCardBrandEnum.EMV_CARD_BRAND_MASTER,
                "A0000000651010" to EmvCardBrandEnum.EMV_CARD_BRAND_UNIONPAY,
                // Añade más mapeos según sea necesario
                "A0000005241010" to EmvCardBrandEnum.EMV_CARD_BRAND_VISA // Ejemplo adicional
            )

            for (aidEntity in aidEntityList) {
                try {
                    // Convertir el AID a ByteArray
                    val aidBytes = ByteUtils.hexString2ByteArray(aidEntity.aid)

                    // Determinar la marca de la tarjeta
                    val cardBrand = aidToBrandMap.getOrDefault(
                        aidEntity.aid,
                        EmvCardBrandEnum.EMV_CARD_BRAND_VISA // Valor predeterminado
                    )

                    // Intentar eliminar el AID existente (si existe)
                    emvHandler2?.delOneAid(aidBytes)

                    // Intentar agregar el AID
                    val result = emvHandler2?.contactlessAppendAidIntoKernel(
                        cardBrand,
                        0x01.toByte(),
                        aidBytes
                    ) ?: -1

                    if (result > 0) {
                        successCount++
                        Log.d(
                            "nexgo",
                            "AID agregado exitosamente: ${aidEntity.aid}, Marca: $cardBrand"
                        )
                    } else {
                        failureCount++
                        Log.e(
                            "nexgo",
                            "Error al agregar AID: ${aidEntity.aid}, Código de error: $result"
                        )
                    }
                } catch (e: Exception) {
                    failureCount++
                    Log.e("nexgo", "Excepción al agregar AID: ${aidEntity.aid}", e)
                }
            }

            // Verificar el resultado final
            Log.d("nexgo", "Resumen de AID: Agregados=$successCount, Fallidos=$failureCount")

            // Considerar éxito si al menos un AID se agregó correctamente
            return successCount > 0
        } catch (e: Exception) {
            Log.e("nexgo", "Excepción durante la inicialización de AID", e)
            e.printStackTrace()
            return false
        }
    }

    fun initEmvCapk(): Boolean {
        // Eliminar todos los CAPK existentes
        emvHandler2?.delAllCapk()

        // Verificar número actual de CAPK
        val currentCapkNum = emvHandler2?.capkListNum ?: 0
        Log.d("nexgo", "Número actual de CAPK: $currentCapkNum")

        // Obtener lista de CAPK desde EmvUtils
        val capkEntityList = EmvUtils.capkList

        // Verificar si la lista de CAPK está vacía o nula
        if (capkEntityList == null || capkEntityList.isEmpty()) {
            Log.e("nexgo", "Error: Lista de CAPK está vacía o nula")
            return false
        }

        // Registrar detalles de cada CAPK
        capkEntityList.forEachIndexed { index, capkEntity ->
            Log.d(
                "nexgo", "CAPK $index: RID: ${capkEntity.rid}, Índice de clave: ${capkEntity.rid}"
            )
        }

        // Intentar establecer la lista de CAPK
        val result = emvHandler2?.setCAPKList(capkEntityList) ?: -1

        // Verificar el resultado de la configuración
        return result > 0.also { success ->
            if (success == 0) {
                Log.d("nexgo", "CAPK inicializado exitosamente. Número de CAPK: $result")
            } else {
                Log.e("nexgo", "Error crítico al establecer CAPK. Código de error: $result")
            }
        }
    }

    fun checkAid(): Pair<Int, Int> {
        val aidNum = emvHandler2?.aidListNum ?: 0
        val capkNum = emvHandler2?.capkListNum ?: 0
        Log.d("nexgo", "getAidListNum $aidNum")
        Log.d("nexgo", "getCapkListNum $capkNum")
        return Pair(aidNum, capkNum)
    }

    fun getAidList() = emvHandler2?.aidList

    fun getCapkList() = emvHandler2?.capkList

    fun cancelEmvProcess() {
        emvHandler2?.emvProcessCancel()
    }

    fun onApplicationSelected(position: Int) {
        emvHandler2?.onSetSelAppResponse(position + 1)
    }

    fun onCardNumberConfirmed(confirmed: Boolean) {
        emvHandler2?.onSetConfirmCardNoResponse(confirmed)
    }

    fun onPinInputComplete(success: Boolean, isNoPin: Boolean) {
        emvHandler2?.onSetPinInputResponse(success, isNoPin)
    }

    fun configurePaypassParameter(aid: ByteArray) {
        // Configuración del kernel, habilitar RRP y cdcvm
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x1B.toByte()), byteArrayOf(0x30.toByte())
        )

        // EMV MODE: monto > límite cvm sin contacto, establecer 60 = pin en línea y firma
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()), byteArrayOf(0x60.toByte())
        )

        // EMV mode: monto < límite cvm sin contacto, establecer 08 = sin cvm
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()), byteArrayOf(0x08.toByte())
        )

        // Configuración específica para Maestro
        if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                .contains("A0000000043060")
        ) {
            Log.d("nexgo", "======maestro===== ")
            // Maestro solo soporta PIN en línea
            emvHandler2?.setTlv(
                byteArrayOf(0x9F.toByte(), 0x33.toByte()),
                byteArrayOf(0xE0.toByte(), 0x40.toByte(), 0xC8.toByte())
            )
            emvHandler2?.setTlv(
                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()), byteArrayOf(0x40.toByte())
            )
            emvHandler2?.setTlv(
                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()), byteArrayOf(0x08.toByte())
            )
        }
    }

    fun getTlv(tag: ByteArray): ByteArray? {
        return emvHandler2?.getTlv(tag, EmvDataSourceEnum.FROM_KERNEL)
    }

    fun getEmvCardDataInfo() = emvHandler2?.emvCardDataInfo

    fun getEmvCvmResult() = emvHandler2?.emvCvmResult

    fun getSignNeed() = emvHandler2?.signNeed

    fun getEmvContactlessMode() = emvHandler2?.emvContactlessMode

    fun setOnlineProcResponse(resultCode: Int, emvOnlineResult: EmvOnlineResultEntity) {
        emvHandler2?.onSetOnlineProcResponse(resultCode, emvOnlineResult)
    }

    fun setRemoveCardResponse() {
        emvHandler2?.onSetRemoveCardResponse()
    }

    fun setPromptResponse(response: Boolean) {
        emvHandler2?.onSetPromptResponse(response)
    }

    fun setTransInitBeforeGPOResponse(response: Boolean) {
        emvHandler2?.onSetTransInitBeforeGPOResponse(response)
    }

    fun createTransactionConfiguration(
        amount: String, transType: Byte = 0x00, cardExistSlot: CardSlotTypeEnum
    ): EmvTransConfigurationEntity {
        val transData = EmvTransConfigurationEntity()
        transData.transAmount = amount
        transData.emvTransType =
            transType // 0x00-sale, otros valores para otros tipos de transacción
        transData.countryCode = "840" // CountryCode
        transData.currencyCode = "840" // CurrencyCode, 840 indicate USD dollar
        transData.termId = "00000001"
        transData.merId = "000000000000001"
        transData.transDate = SimpleDateFormat("yyMMdd", Locale.getDefault()).format(Date())
        transData.transTime = SimpleDateFormat("hhmmss", Locale.getDefault()).format(Date())
        transData.traceNo = "00000000"

        // Configurar el modo de entrada según el tipo de tarjeta
        if (cardExistSlot == CardSlotTypeEnum.RF) {
            transData.emvEntryModeEnum =
                com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS
        } else {
            transData.emvEntryModeEnum =
                com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACT
        }

        // Configuración para UPI
        val unionPayTransDataEntity = com.nexgo.oaf.apiv3.emv.UnionPayTransDataEntity()
        unionPayTransDataEntity.setQpbocForGlobal(true)
        unionPayTransDataEntity.isSupportCDCVM = true
        transData.unionPayTransDataEntity = unionPayTransDataEntity

        return transData
    }

    fun getEmvHandler(): EmvHandler2? = emvHandler2

    fun buildField55(): String {
        // Lista de tags EMV comúnmente requeridos para autorización
        val commonTags = arrayOf(
            byteArrayOf(0x5F, 0x2A.toByte()),        // 5F2A - Moneda
            byteArrayOf(0x82.toByte()),              // 82 - AIP
            byteArrayOf(0x84.toByte()),              // 84 - DF Name
            byteArrayOf(0x95.toByte()),              // 95 - TVR
            byteArrayOf(0x9A.toByte()),              // 9A - Fecha de transacción
            byteArrayOf(0x9C.toByte()),              // 9C - Tipo de transacción
            byteArrayOf(0x9F.toByte(), 0x02.toByte()),        // 9F02 - Monto
            byteArrayOf(0x9F.toByte(), 0x03.toByte()),        // 9F03 - Monto adicional
            byteArrayOf(
                0x9F.toByte(), 0x10.toByte()
            ),        // 9F10 - Datos de aplicación específicos del emisor
            byteArrayOf(0x9F.toByte(), 0x1A.toByte()),        // 9F1A - Código de país del terminal
            byteArrayOf(0x9F.toByte(), 0x26.toByte()),        // 9F26 - Criptograma de aplicación
            byteArrayOf(
                0x9F.toByte(), 0x27.toByte()
            ),        // 9F27 - Datos de información del criptograma
            byteArrayOf(
                0x9F.toByte(), 0x36.toByte()
            ),        // 9F36 - Contador de transacciones de aplicación
            byteArrayOf(
                0x9F.toByte(), 0x37.toByte()
            )         // 9F37 - Número aleatorio no predecible
        )

        val field55Builder = StringBuilder()

        for (tag in commonTags) {
            val value = emvHandler2?.getTlv(tag, EmvDataSourceEnum.FROM_KERNEL)
            if (value != null) {
                val tagStr = ByteUtils.byteArray2HexString(tag)
                val lenStr = String.format("%02X", value.size)
                val valueStr = ByteUtils.byteArray2HexString(value)
                field55Builder.append(tagStr).append(lenStr).append(valueStr)
            }
        }

        return field55Builder.toString()
    }
}