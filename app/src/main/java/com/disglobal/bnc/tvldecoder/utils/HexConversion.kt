// Archivo: HexConversion.kt (Corregido)
package com.disglobal.bnc.tvldecoder.utils

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import java.util.Base64


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

object DukptDecryption {
    private val IV = ByteArray(8) // IV de 8 bytes en 0x00

    fun decryptTripleDES(encryptedHex: String, keyHex: String): String {
        val keyBytes = hexStringToByteArray(keyHex)
        val encryptedBytes = hexStringToByteArray(encryptedHex)

        val keySpec = SecretKeySpec(keyBytes, "DESede")
        val cipher = Cipher.getInstance("DESede/CBC/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(IV))

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return decryptedBytes.toString(Charsets.UTF_8).trim { it <= ' ' }
    }

    private fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        for (i in 0 until len step 2) {
            data[i / 2] =
                ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        }
        return data
    }
}
