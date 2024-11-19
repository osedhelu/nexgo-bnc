fun decodeTLV(data: ByteArray): TLV {
    val tagLength = if (data[0].toInt() and 0x1F == 0x1F) 2 else 1
    val tag = data.take(tagLength).joinToString("") { String.format("%02X", it) }
    val lengthByte = data[tagLength].toInt()
    val length: Int
    val valueStartIndex: Int

    if (tag == "C2") {
        // Validación específica para el tag C2
        if (lengthByte and 0x80 == 0x80 && (lengthByte and 0x7F) == 2) {
            length = 0x0178 // 376 bytes
            valueStartIndex = tagLength + 1 + 2 // 2 bytes for length
        } else {
            throw IllegalArgumentException("Invalid length for tag C2")
        }
    } else if (lengthByte and 0x80 == 0x80) {
        val numLengthBytes = lengthByte and 0x7F
        length = ByteBuffer.wrap(data, tagLength + 1, numLengthBytes).int
        valueStartIndex = tagLength + 1 + numLengthBytes
    } else {
        length = lengthByte
        valueStartIndex = tagLength + 1
    }

    val value =
        data.drop(valueStartIndex).take(length).joinToString("") { String.format("%02X", it) }

    return TLV(tag, length, value)
}
