package com.disglobal.bnc.DigipayApi

import com.nexgo.common.ByteUtils
import com.nexgo.oaf.apiv3.emv.EmvDataSourceEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import java.util.HashMap

/**
 * Clase para construir y procesar mensajes TLV para la API de Digipay
 */
class TlvBuilder {
    companion object {
        // Constantes para los tipos de mensaje
        const val MESSAGE_TYPE_SALE = "0200"           // Venta
        const val MESSAGE_TYPE_COMPLETION = "0220"     // Completado (Advice)
        const val MESSAGE_TYPE_VOID = "0222"           // Anulación
        const val MESSAGE_TYPE_REVERSAL = "0400"       // Reverso
        const val MESSAGE_TYPE_APPROVED = "0620"       // Transacción aprobada (Advice)
        const val MESSAGE_TYPE_DECLINED = "0320"       // Transacción declinada (Advice)

        // Tags EMV comunes
        val TAG_TRACK2_DATA = byteArrayOf(0x57)                        // Track 2 Equivalent Data
        val TAG_PAN =
            byteArrayOf(0x5A)                                // Application Primary Account Number (PAN)
        val TAG_TVR =
            byteArrayOf(0x95.toByte())                                // Terminal Verification Results
        val TAG_TRANSACTION_DATE = byteArrayOf(0x9A.toByte())                   // Transaction Date
        val TAG_TSI =
            byteArrayOf(0x9B.toByte())                                // Transaction Status Information
        val TAG_TRANSACTION_TYPE = byteArrayOf(0x9C.toByte())                   // Transaction Type
        val TAG_CARDHOLDER_NAME = byteArrayOf(0x5F, 0x20)              // Cardholder Name
        val TAG_APP_EXPIRATION_DATE =
            byteArrayOf(0x5F, 0x24)          // Application Expiration Date
        val TAG_TRANSACTION_CURRENCY_CODE = byteArrayOf(0x5F, 0x2A)    // Transaction Currency Code
        val TAG_SERVICE_CODE = byteArrayOf(0x5F, 0x30)                 // Service Code
        val TAG_AMOUNT_AUTHORIZED =
            byteArrayOf(0x9F.toByte(), 0x02)            // Amount, Authorized
        val TAG_MERCHANT_ID =
            byteArrayOf(0x9F.toByte(), 0x16)                  // Merchant Identifier
        val TAG_TERMINAL_COUNTRY_CODE =
            byteArrayOf(0x9F.toByte(), 0x1A)        // Terminal Country Code
        val TAG_TERMINAL_ID =
            byteArrayOf(0x9F.toByte(), 0x1C)                  // Terminal Identification
        val TAG_INTERFACE_DEVICE_SERIAL =
            byteArrayOf(0x9F.toByte(), 0x1E)      // Interface Device Serial Number
        val TAG_TRANSACTION_TIME = byteArrayOf(0x9F.toByte(), 0x21)             // Transaction Time
        val TAG_TERMINAL_CAPABILITIES =
            byteArrayOf(0x9F.toByte(), 0x33)        // Terminal Capabilities
        val TAG_TERMINAL_TYPE = byteArrayOf(0x9F.toByte(), 0x35)                // Terminal Type
        val TAG_ATC = byteArrayOf(
            0x9F.toByte(), 0x36
        )                          // Application Transaction Counter
        val TAG_UNPREDICTABLE_NUMBER =
            byteArrayOf(0x9F.toByte(), 0x37)         // Unpredictable Number
        val TAG_POS_ENTRY_MODE =
            byteArrayOf(0x9F.toByte(), 0x39)               // Point-of-Service Entry Mode
        val TAG_ADDITIONAL_TERMINAL_CAPABILITIES =
            byteArrayOf(0x9F.toByte(), 0x40) // Additional Terminal Capabilities
        val TAG_TRANSACTION_SEQUENCE_COUNTER =
            byteArrayOf(0x9F.toByte(), 0x41) // Transaction Sequence Counter
        val TAG_MERCHANT_NAME_LOCATION =
            byteArrayOf(0x9F.toByte(), 0x4E)       // Merchant Name and Location

        // Tags específicos para criptogramas
        val TAG_APPLICATION_CRYPTOGRAM =
            byteArrayOf(0x9F.toByte(), 0x26)       // Application Cryptogram
        val TAG_CRYPTOGRAM_INFO_DATA =
            byteArrayOf(0x9F.toByte(), 0x27)         // Cryptogram Information Data
        val TAG_ISSUER_APP_DATA =
            byteArrayOf(0x9F.toByte(), 0x10)              // Issuer Application Data
        val TAG_AIP =
            byteArrayOf(0x82.toByte())                                // Application Interchange Profile
        val TAG_AID =
            byteArrayOf(0x84.toByte())                                // Dedicated File Name (AID)
        val TAG_APPLICATION_LABEL = byteArrayOf(0x50)                  // Application Label

        // Tags personalizados para Digipay
        val TAG_KSN_DATA_KEY = byteArrayOf(0xC0.toByte())              // KSN (Data encryption Key)
        val TAG_KSN_PIN_KEY = byteArrayOf(0xC1.toByte())               // KSN (PIN encryption Key)
        val TAG_ENCRYPTED_DATA = byteArrayOf(0xC2.toByte())            // Encrypted Data
        val TAG_MESSAGE_TYPE =
            byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x30.toByte()) // Message Type
        val TAG_RETRIEVAL_REFERENCE_NUMBER =
            byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x31.toByte()) // Retrieval Reference Number
        val TAG_BATCH_NUMBER =
            byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x32.toByte()) // Batch Number
        val TAG_STAN =
            byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x33.toByte()) // System Trace Audit Number
        val TAG_INVOICE_NUMBER =
            byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x34.toByte()) // Invoice Number
        val TAG_RECEIPT = byteArrayOf(0xDF.toByte(), 0x83.toByte(), 0x2A.toByte()) // Receipt

        /**
         * Construye un mensaje TLV para una transacción de venta
         * @param emvHandler2 Manejador EMV para obtener los datos de la tarjeta
         * @param amount Monto de la transacción
         * @param merchantId ID del comerciante
         * @param terminalId ID del terminal
         * @param batchNumber Número de lote
         * @param stan System Trace Audit Number
         * @param invoiceNumber Número de factura
         * @param ksn Key Serial Number
         * @return Mensaje TLV en formato hexadecimal
         */
        fun buildSaleTlvMessage(
            cardInfo: Map<String, String>, field55: ByteArray, affiliateInfo: GetInfoAffiliatesResp?
        ): String {
            // Verificar que el emvHandler2 no sea nulo
            if (emvHandler2 == null) {
                throw IllegalArgumentException("EmvHandler2 no puede ser nulo")
            }

            // Construir el mapa de TLVs para la transacción
            val tlvMap = HashMap<String, String>()

            // Agregar el tipo de mensaje (Venta)
            tlvMap[ByteUtils.byteArray2HexString(TAG_MESSAGE_TYPE)] = MESSAGE_TYPE_SALE

            // Agregar datos de la transacción
            tlvMap[ByteUtils.byteArray2HexString(TAG_BATCH_NUMBER)] = batchNumber
            tlvMap[ByteUtils.byteArray2HexString(TAG_STAN)] = stan
            tlvMap[ByteUtils.byteArray2HexString(TAG_INVOICE_NUMBER)] = invoiceNumber
            tlvMap[ByteUtils.byteArray2HexString(TAG_MERCHANT_ID)] = merchantId
            tlvMap[ByteUtils.byteArray2HexString(TAG_TERMINAL_ID)] = terminalId

            // Agregar KSN para cifrado
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_DATA_KEY)] = ksn
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_PIN_KEY)] = ksn

            // Obtener y agregar datos EMV
            addEmvDataToTlvMap(emvHandler2, tlvMap)

            // Agregar el monto autorizado
            val formattedAmount = formatAmount(amount)
            val amountBytes = ByteUtils.hexString2ByteArray(formattedAmount)
            tlvMap[ByteUtils.byteArray2HexString(TAG_AMOUNT_AUTHORIZED)] =
                ByteUtils.byteArray2HexString(amountBytes)

            // Construir el campo 55 (datos EMV)
            val field55 = buildField55(emvHandler2)

            // TODO: Cifrar los datos según el formato requerido por Digipay
            // Aquí se debería implementar el cifrado DUKPT con la clave derivada del KSN

            // Por ahora, simplemente devolvemos los datos sin cifrar
            return buildTlvString(tlvMap)
        }

        /**
         * Construye un mensaje TLV para una transacción de advice (completado)
         * @param emvHandler2 Manejador EMV para obtener los datos de la tarjeta
         * @param originalTlv TLV original de la transacción de venta
         * @param approvalCode Código de aprobación
         * @param ksn Key Serial Number
         * @return Mensaje TLV en formato hexadecimal
         */
        fun buildAdviceTlvMessage(
            emvHandler2: EmvHandler2?, originalTlv: String, approvalCode: String, ksn: String
        ): String {
            // Verificar que el emvHandler2 no sea nulo
            if (emvHandler2 == null) {
                throw IllegalArgumentException("EmvHandler2 no puede ser nulo")
            }

            // Construir el mapa de TLVs para la transacción
            val tlvMap = HashMap<String, String>()

            // Agregar el tipo de mensaje (Advice)
            tlvMap[ByteUtils.byteArray2HexString(TAG_MESSAGE_TYPE)] = MESSAGE_TYPE_APPROVED

            // Agregar KSN para cifrado
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_DATA_KEY)] = ksn
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_PIN_KEY)] = ksn

            // TODO: Parsear el TLV original y agregar los datos necesarios
            // Aquí se debería implementar el parsing del TLV original

            // Por ahora, simplemente devolvemos un mensaje básico
            return buildTlvString(tlvMap)
        }

        /**
         * Construye un mensaje TLV para una transacción de reverso
         * @param originalTlv TLV original de la transacción de venta
         * @param ksn Key Serial Number
         * @return Mensaje TLV en formato hexadecimal
         */
        fun buildReversalTlvMessage(
            originalTlv: String, ksn: String
        ): String {
            // Construir el mapa de TLVs para la transacción
            val tlvMap = HashMap<String, String>()

            // Agregar el tipo de mensaje (Reverso)
            tlvMap[ByteUtils.byteArray2HexString(TAG_MESSAGE_TYPE)] = MESSAGE_TYPE_REVERSAL

            // Agregar KSN para cifrado
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_DATA_KEY)] = ksn
            tlvMap[ByteUtils.byteArray2HexString(TAG_KSN_PIN_KEY)] = ksn

            // TODO: Parsear el TLV original y agregar los datos necesarios
            // Aquí se debería implementar el parsing del TLV original

            // Por ahora, simplemente devolvemos un mensaje básico
            return buildTlvString(tlvMap)
        }

        /**
         * Agrega los datos EMV al mapa TLV
         * @param emvHandler2 Manejador EMV para obtener los datos de la tarjeta
         * @param tlvMap Mapa TLV donde se agregarán los datos
         */
        private fun addEmvDataToTlvMap(emvHandler2: EmvHandler2, tlvMap: HashMap<String, String>) {
            // Lista de tags EMV a obtener
            val emvTags = arrayOf(
                TAG_TRACK2_DATA,
                TAG_PAN,
                TAG_TVR,
                TAG_TRANSACTION_DATE,
                TAG_TSI,
                TAG_TRANSACTION_TYPE,
                TAG_CARDHOLDER_NAME,
                TAG_APP_EXPIRATION_DATE,
                TAG_TRANSACTION_CURRENCY_CODE,
                TAG_SERVICE_CODE,
                TAG_TERMINAL_COUNTRY_CODE,
                TAG_TERMINAL_ID,
                TAG_INTERFACE_DEVICE_SERIAL,
                TAG_TRANSACTION_TIME,
                TAG_TERMINAL_CAPABILITIES,
                TAG_TERMINAL_TYPE,
                TAG_ATC,
                TAG_UNPREDICTABLE_NUMBER,
                TAG_POS_ENTRY_MODE,
                TAG_ADDITIONAL_TERMINAL_CAPABILITIES,
                TAG_TRANSACTION_SEQUENCE_COUNTER,
                TAG_MERCHANT_NAME_LOCATION,
                TAG_APPLICATION_CRYPTOGRAM,
                TAG_CRYPTOGRAM_INFO_DATA,
                TAG_ISSUER_APP_DATA,
                TAG_AIP,
                TAG_AID,
                TAG_APPLICATION_LABEL
            )

            // Obtener y agregar cada tag al mapa
            for (tag in emvTags) {
                val value = emvHandler2.getTlv(tag, EmvDataSourceEnum.FROM_KERNEL)
                if (value != null) {
                    tlvMap[ByteUtils.byteArray2HexString(tag)] =
                        ByteUtils.byteArray2HexString(value)
                }
            }
        }

        /**
         * Construye el campo 55 (datos EMV) para enviar al servidor
         * @param emvHandler2 Manejador EMV para obtener los datos de la tarjeta
         * @return Campo 55 en formato hexadecimal
         */
        fun buildField55(emvHandler2: EmvHandler2): String {
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
                byteArrayOf(
                    0x9F.toByte(), 0x1A.toByte()
                ),        // 9F1A - Código de país del terminal
                byteArrayOf(
                    0x9F.toByte(), 0x26.toByte()
                ),        // 9F26 - Criptograma de aplicación
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
                val value = emvHandler2.getTlv(tag, EmvDataSourceEnum.FROM_KERNEL)
                if (value != null) {
                    val tagStr = ByteUtils.byteArray2HexString(tag)
                    val lenStr = String.format("%02X", value.size)
                    val valueStr = ByteUtils.byteArray2HexString(value)
                    field55Builder.append(tagStr).append(lenStr).append(valueStr)
                }
            }

            return field55Builder.toString()
        }

        /**
         * Construye una cadena TLV a partir de un mapa de tags y valores
         * @param tlvMap Mapa de tags y valores en formato hexadecimal
         * @return Cadena TLV en formato hexadecimal
         */
        private fun buildTlvString(tlvMap: HashMap<String, String>): String {
            val builder = StringBuilder()

            for ((tag, value) in tlvMap) {
                val valueBytes = ByteUtils.hexString2ByteArray(value)
                val lenStr = String.format("%02X", valueBytes.size)
                builder.append(tag).append(lenStr).append(value)
            }

            return builder.toString()
        }

        /**
         * Formatea un monto para enviarlo en el formato requerido por Digipay
         * @param amount Monto en formato decimal (ej: "123.45")
         * @return Monto formateado en hexadecimal (ej: "000000012345")
         */
        private fun formatAmount(amount: String): String {
            // Eliminar el punto decimal y convertir a entero
            val amountInt = amount.replace(".", "").toLong()

            // Formatear a 12 dígitos con ceros a la izquierda
            return String.format("%012d", amountInt)
        }
    }
}