package com.osedhelu.bnc.utils

import java.nio.ByteBuffer

data class TLV(val tag: String, val length: Int, val value: String)

fun encodeTLV(tlv: TLV): ByteArray {
    val tagBytes = tlv.tag.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val lengthBytes =
        ByteBuffer.allocate(4).putInt(tlv.length).array().dropWhile { it == 0.toByte() }
            .toByteArray()
    val valueBytes = tlv.value.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    return tagBytes + lengthBytes + valueBytes
}

fun decodeTLV(data: ByteArray): TLV {
    val tagLength = if (data[0].toInt() and 0x1F == 0x1F) 2 else 1
    val tag = data.take(tagLength).joinToString("") { String.format("%02X", it) }
    val lengthByte = data[tagLength].toInt()
    val length: Int
    val valueStartIndex: Int

    if (lengthByte and 0x80 == 0x80) {
        val numLengthBytes = lengthByte and 0x7F
        length = ByteBuffer.wrap(data, tagLength + 1, numLengthBytes).int
        valueStartIndex = tagLength + 1 + numLengthBytes
    } else {
        length = lengthByte
        valueStartIndex = tagLength + 1
    }

    val value = data.drop(valueStartIndex).take(length)
        .joinToString("") { String.format("%02X", it) }

    return TLV(tag, length, value)
}

fun decodeTLVList(data: ByteArray): List<TLV> {
    val tlvs = mutableListOf<TLV>()
    var index = 0
    while (index < data.size) {
        val tlv = decodeTLV(data.drop(index).toByteArray())
        tlvs.add(tlv)
        index += tlv.tag.length / 2 + tlv.length + if (tlv.length > 127) 2 else 1
    }
    return tlvs
}

fun main() {
    var data =
        """
            9F2608C6D4E559DC295C0B9F2701809F100706010A03A000009F3704B8268C929F36020356950500400080009A031102159C01009F02060000000010005F2A02093782025C009F1A0208629F34031E03009F3303E0F8C89F03060000000000009F1E0832303833333536369F21031113229F350122
        """.trimIndent()
    println(data)
    data = data.trimIndent().replace("\n", "").replace(" ", "")
    val dataBytes = data.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val tlvs = decodeTLVList(dataBytes)

    tlvs.forEach { tlv ->
        println("Tag: ${tlv.tag}, Length: ${tlv.length}, Value: ${tlv.value}")
    }
    val tlvsToEncode = listOf(
        TLV("9F26", 8, "C6D4E559DC295C0B"),
        TLV("9F27", 1, "80"),
        TLV("9F10", 7, "06010A03A00000"),
        TLV("9F37", 4, "B8268C92"),
        TLV("9F36", 2, "0356"),
        TLV("95", 5, "0040008000"),
        TLV("9A", 3, "110215"),
        TLV("9C", 1, "00"),
        TLV("9F02", 6, "000000001000"),
        TLV("5F2A", 2, "0937"),
        TLV("82", 2, "5C00"),
        TLV("9F1A", 2, "0862"),
        TLV("9F34", 3, "1E0300"),
        TLV("9F33", 3, "E0F8C8"),
        TLV("9F03", 6, "000000000000"),
        TLV("9F1E", 8, "3230383333353636"),
        TLV("9F21", 3, "111322"),
        TLV("9F35", 1, "22")
    )

    val encodedBytes = tlvsToEncode.flatMap { encodeTLV(it).asIterable() }.toByteArray()
    val encodedHexString = encodedBytes.joinToString("") { String.format("%02X", it) }

    println("Encoded TLV: $encodedHexString")

}