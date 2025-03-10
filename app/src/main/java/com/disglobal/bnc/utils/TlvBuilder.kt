package com.disglobal.bnc.utils

class TlvBuilder {
    private val tags = mutableMapOf<String, String>()

    fun addTag(tag: String, value: String): TlvBuilder {
        tags[tag] = value
        return this
    }

    fun build(): String {
        return tags.entries.joinToString("") { (tag, value) ->
            "${tag.padStart(4, '0')}${value.length.toString().padStart(4, '0')}$value"
        }
    }

    fun parse(tlvString: String): Map<String, String> {
        val result = mutableMapOf<String, String>()
        var index = 0

        while (index < tlvString.length) {
            val tag = tlvString.substring(index, index + 4)
            index += 4
            val lengthStr = tlvString.substring(index, index + 4)
            index += 4
            val length = lengthStr.toInt()
            val value = tlvString.substring(index, index + length)
            index += length

            result[tag] = value
        }

        return result
    }
}