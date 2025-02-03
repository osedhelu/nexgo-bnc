package com.disglobal.bnc.tvldecoder.utils

import com.disglobal.bnc.tvldecoder.model.Tlv
import com.disglobal.bnc.tvldecoder.store.EMVTagStore

class TlvDecoder {
    companion object {
        private val TAG_LENGTHS = listOf(6, 4, 2)
        private const val MAX_TAG_LENGTH = 6

        fun parseString(hexString: String): MutableList<Tlv> {
            val tlvList = mutableListOf<Tlv>()
            var remaining = hexString

            while (remaining.length >= 4) {
                val tag = detectTag(remaining) ?: break
                remaining = remaining.substring(tag.length)

                if (remaining.length < 2) break

                val (lengthValue, bytesConsumed) = parseLength(remaining) ?: break
                remaining = remaining.substring(bytesConsumed * 2)

                val totalValueLength = lengthValue * 2
                if (remaining.length < totalValueLength) break

                val value = remaining.substring(0, totalValueLength)
                remaining = remaining.substring(totalValueLength)

                tlvList.add(createTlv(tag, value))
            }

            return tlvList
        }

        private fun detectTag(input: String): String? {
            return TAG_LENGTHS.firstOrNull { length ->
                length <= input.length && EMVTagStore.emvMap.containsKey(input.substring(0, length))
            }?.let { input.substring(0, it) }
        }

        private fun parseLength(input: String): Pair<Int, Int>? {
            return when (val firstByte = HexConversion.ToInt(input.substring(0..1))) {
                in 0..127 -> Pair(firstByte, 1)
                0x81 -> if (input.length >= 4) Pair(
                    HexConversion.ToInt(input.substring(2..3)),
                    2
                ) else null

                0x82 -> if (input.length >= 6) Pair(
                    HexConversion.ToInt(input.substring(2..5)),
                    3
                ) else null

                else -> null
            }
        }

        private fun createTlv(tag: String, value: String): Tlv {
            val emvTag = EMVTagStore.emvMap[tag]
            val decodedValue = if (emvTag?.encoded == true) HexConversion.ToAscii(value) else value

            return Tlv(
                tag = tag,
                tagName = emvTag?.name ?: "Unknown",
                value = decodedValue,
                hexValueLength = value.length
            )
        }
    }
}