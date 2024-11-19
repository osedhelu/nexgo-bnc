package com.disglobal.bnc.utils

fun parseTLV(data: String): TLV {
    var index = 0

    // Read the tag
    var tag = data.substring(index, index + 2)
    index += 2
    if (tag.toInt(16) and 0x1F == 0x1F) {
        tag += data.substring(index, index + 2)
        index += 2
    }
    // Special case for tag DF83
    var lengthHex: String
    var length: Int

    // Read the length
    lengthHex = data.substring(index, index + 2)
    length = lengthHex.toInt(16)
    index += 2
    println("xxxxxx $lengthHex xxxx ${tag.length}")
    if (length == 0x81) {
        println("xxxxxx81 $tag")
        lengthHex = data.substring(index, index + 2)
        length = lengthHex.toInt(16)
        index += 2
    } else if (length == 0x82) {
        println("xxxxxx82 $tag")

        lengthHex = data.substring(index, index + 4)
        length = lengthHex.toInt(16)
        index += 4
    } else if (length == 0x83) {
        println("xxxxxxxxxxxxx83 $tag")
        lengthHex = data.substring(index, index + 6)
        length = lengthHex.toInt(16)
        index += 6
    }

    val value = if (index + length * 2 > data.length) {
        val code = data.substring(index, index + 2)
        val tmpData = data.substring(index, data.length)
        tmpData.substring(0, code.toInt(16) * 2)
    } else {
        data.substring(index, index + length * 2)
    }
    // Read the value

    index += length * 2

    println("tag: $tag, leng: $lengthHex, value: $value")
    // Add the TLV to the list
    return TLV(tag, lengthHex, value)

}


