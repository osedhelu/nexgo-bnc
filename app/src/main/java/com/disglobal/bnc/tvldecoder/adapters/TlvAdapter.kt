// Archivo: Main.kt (Pruebas)
package com.disglobal.bnc.tvldecoder

import com.disglobal.bnc.tvldecoder.model.Tlv
import com.disglobal.bnc.tvldecoder.store.EMVTagStore


fun main() {
    val hexString =
        "DF8330020200C28201A0F7CC36152E50AE1A67BC7C5A60F751CB1FC1453F67232CB5D78B466770D687A206E22717BBBF6F4C918885FEE5D022B901393878FB8AA94ADBA2D6BF532CFABA8C6AB7838EFF0C7135FA6169021EE1948E072431A1005E554BB1BA71A00ED9124C5EE1AE264ACF4C56531FB64CD5CDC69764B670C66D5024147640AC31D5D202D43458D25B63A9F58BA3F4F00D7192C5CC5FCFD9DF41BAA698E52D1826AFCB4562DDDAA170FCD0AE5B3B139AA97784FE0B4B17A5505EAC8E414CAB3D66718CD56C8991D103E1C11A955B11DDE1C5648177C0A4944B710C9170271C9F190C7234A20DA95651460204AAF4F7B03B1DA9FC5B3E845A64241E544A3F2B838BF3D0025C69487E3D98C7E25B859FD95807AA596C3F68B972BBAA397DEF01920E447129D7A281525BCF55FAB77939E65DD81CE67852C56F80C10AA7611F11C2F634A150739B4ED1B3439B98F05FBF9E871EC5B7ADEBF6BBAB07EE7F364AFCD9B80E224DAB03422A882ED73286C6D8B75A1A9722BC5E3A6B11CF6A3386DD101EC67EB16CB70E2A8BDD75F3E60E8FD0D88E38863F5619FBDE749A325988B890F5BEE57C93C00AFFFF9876543210E00004C4075327FFFFFF77399F530152DF834F0D50424A37323241583331343836DF83440A56313333383439393130DF836B050000258963DF834C053030303035DF834B143030303030303030303030303030303030303034"

    val tlvList = parseString(hexString)
    printResults(tlvList)
}

/**
 * Función para analizar una cadena TLV y extraer tags, longitud y valores.
 */

fun parseString(hexString: String): List<Tlv> {
    val tlvList = mutableListOf<Tlv>()
    var remaining = hexString

    while (remaining.isNotEmpty()) {
        // Detecta el tag usando el EMVTagStore
        val tag = detectTag(remaining)
        if (tag.isEmpty()) break

        remaining = remaining.substring(tag.length)

        // Si no hay suficiente data para la longitud, salimos
        if (remaining.length < 2) break

        // Parseamos la longitud
        val (lengthValue, lengthBytes) = parseLength(remaining)
        remaining = remaining.substring(lengthBytes * 2)

        // Verificamos que haya suficientes caracteres para el valor
        if (remaining.length < lengthValue * 2) break
        val valueHex = remaining.substring(0, lengthValue * 2)
        remaining = remaining.substring(lengthValue * 2)

        // Buscamos el nombre del tag a partir del store (si no existe, "Unknown")
        val tagName = EMVTagStore.emvMap[tag]?.name ?: "Unknown"

        tlvList.add(Tlv(tag, tagName, valueHex, valueHex.length))
    }

    return tlvList
}

/**
 * Función para detectar el tag (1 o 2 bytes).
 */
private fun detectTag(input: String): String {
    // Obtiene las keys definidas en el store ordenadas de mayor a menor longitud
    val sortedKeys = EMVTagStore.emvMap.keys.sortedByDescending { it.length }
    for (key in sortedKeys) {
        if (input.startsWith(key, ignoreCase = true)) {
            return key
        }
    }
    // Fallback: toma los primeros 2 caracteres
    return if (input.length >= 2) input.substring(0, 2) else ""
}


private fun parseLength(input: String): Pair<Int, Int> {
    val firstByte = input.substring(0, 2).toInt(16)
    return when {
        firstByte < 0x80 -> Pair(firstByte, 1)
        firstByte == 0x81 -> Pair(input.substring(2, 4).toInt(16), 2)
        firstByte == 0x82 -> Pair(input.substring(2, 6).toInt(16), 3)
        else -> Pair(0, 1)
    }
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