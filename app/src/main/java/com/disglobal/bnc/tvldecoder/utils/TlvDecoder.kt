package com.disglobal.bnc.tvldecoder.utils

import com.disglobal.bnc.tvldecoder.model.Tlv
import com.disglobal.bnc.tvldecoder.store.EMVTagStore

object TlvDecoder {

    /**
     * Función para analizar una cadena TLV y extraer tags, longitud y valores.
     */
    fun parseString(hexString: String): List<Tlv> {
        val tlvList = mutableListOf<Tlv>()
        var remaining = hexString

        while (remaining.isNotEmpty()) {
            // Detectar el tag
            val tag = detectTag(remaining)
            if (tag.isEmpty()) {
                println("Error: No se pudo detectar un tag en la cadena restante: $remaining")
                break
            }

            remaining = remaining.substring(tag.length)

            // Verificar que haya datos suficientes para la longitud
            if (remaining.length < 2) {
                println("Error: No hay suficiente información para leer la longitud del tag $tag")
                break
            }

            // Obtener la longitud del valor
            val (lengthValue, lengthBytes) = parseLength(remaining)
            remaining = remaining.substring(lengthBytes * 2)

            // Verificar que haya suficientes caracteres para el valor
            if (remaining.length < lengthValue * 2) {
                println("Error: No hay suficientes datos para el tag $tag")
                break
            }

            val valueHex = remaining.substring(0, lengthValue * 2)
            remaining = remaining.substring(lengthValue * 2)

            // Obtener el nombre del tag desde el EMVTagStore
            val tagName = EMVTagStore.emvMap[tag]?.name ?: "Unknown"

            // Agregar el tag a la lista
            tlvList.add(Tlv(tag, tagName, valueHex, valueHex.length))
        }

        return tlvList
    }


    /**
     * Función para detectar el tag (1 o 2 bytes).
     */
    private fun detectTag(input: String): String {
        if (input.length < 2) return ""

        // Ordenar los tags de mayor a menor longitud
        val sortedKeys = EMVTagStore.emvMap.keys.sortedByDescending { it.length }
        for (key in sortedKeys) {
            if (input.startsWith(key, ignoreCase = true)) {
                return key
            }
        }

        // Si no está en el EMVTagStore, intentar identificarlo manualmente
        val firstByte = input.substring(0, 2).uppercase()
        return when {
            firstByte == "DF" && input.length >= 6 -> input.substring(
                0, 6
            )  // Tags de 3 bytes (DF83xx)
            firstByte == "DF" && input.length >= 4 -> input.substring(
                0, 4
            )  // Tags de 2 bytes (DFxx)
            firstByte == "9F" && input.length >= 4 -> input.substring(
                0, 4
            )  // Tags de 2 bytes (9Fxx)
            else -> firstByte
        }
    }

    private fun parseLength(input: String): Pair<Int, Int> {
        if (input.length < 2) return Pair(0, 1)

        val firstByte = input.substring(0, 2).toInt(16)
        return when {
            firstByte < 0x80 -> Pair(firstByte, 1)  // Longitud de 1 byte
            firstByte == 0x81 && input.length >= 4 -> Pair(
                input.substring(2, 4).toInt(16), 2
            )  // Longitud de 2 bytes
            firstByte == 0x82 && input.length >= 6 -> Pair(
                input.substring(2, 6).toInt(16), 3
            )  // Longitud de 3 bytes
            else -> {
                println("Error: Longitud inválida en TLV")
                Pair(0, 1)
            }
        }
    }

    fun buildTLV(tlvList: List<Tlv>): String {
        val stringBuilder = StringBuilder()

        for (tlv in tlvList) {
            val lengthHex = tlv.value.length.div(2).toString(16).padStart(2, '0')
            stringBuilder.append(tlv.tag).append(lengthHex).append(tlv.value)
        }

        return stringBuilder.toString().uppercase()
    }

    /**
     * Función para imprimir los resultados en formato tabla.
     */
    fun printResults(tlvList: List<Tlv>) {
        println("| Tag     | Tag name                                  | Value (Hex)                                                 |")
        println("|---------|-------------------------------------------|-------------------------------------------------------------|")
        tlvList.forEach { tlv ->
            println("| ${tlv.tag.padEnd(8)} | ${tlv.tagName.padEnd(41)} | ${tlv.value} |")
        }
    }
}