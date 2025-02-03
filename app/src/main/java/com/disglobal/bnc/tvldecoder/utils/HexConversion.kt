// Archivo: HexConversion.kt (Corregido)
package com.disglobal.bnc.tvldecoder.utils

class HexConversion {
    companion object {
        fun ToInt(hexValue: String): Int = hexValue.toInt(radix = 16)

        fun ToAscii(hexStr: String): String {
            return hexStr.chunked(2)
                .map { it.toInt(16).toChar() }
                .joinToString("")
                .replace(Regex("[^\\x20-\\x7E]"), "")
        }
    }
}