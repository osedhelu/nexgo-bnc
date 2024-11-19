package com.disglobal.bnc.utils

data class TLV(
    val tag: String,

    val length: String,

    val value: String
)

fun getLength(classValue: String): Int {
    return when (classValue) {
        "83" -> 8
        else -> 6
    }
}


fun getTlV(data: String, len: Int, lenCode: Int): TLV {
    val TagName = data.substring(0, len)
    val lenHex = TagName.substring(TagName.length - lenCode)
    println("TagNama ===== $TagName")
    println("lenHex ===== $lenHex")

    val value = data.substring(TagName.length, TagName.length + (lenHex.toInt(16) * 2))

    return TLV(TagName.dropLast(2), lenHex, value)
}

fun getTLVClass(data: String, lenCode: String, len: Int): TLV {
    val initTag = data.substring(0, 2) + data.substring(2, len)
    val allTagAndValue = initTag + lenCode
    val value =
        data.substring(allTagAndValue.length, allTagAndValue.length + (lenCode.toInt(16) * 2))
    return TLV(initTag, lenCode, value)
}

fun decodeTLVList(data: String): List<TLV> {
    val tlvs = mutableListOf<TLV>()
    var i = 0
    var tmpIndex = 0

    while (i < data.length) {
        tmpIndex += 1
        val label = data.substring(i, i + 2)
        val tagState: TLV = when (label) {
            "C0" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                getTLVClass(newData, lenCode, 2)
            }

            "C1" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                getTLVClass(newData, lenCode, 2)
            }

            "C2" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(4, 8)
                getTLVClass(newData, lenCode, 4)
            }

            "C4" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                getTLVClass(newData, lenCode, 2)
            }
            "C5" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                getTLVClass(newData, lenCode, 2)
            }

            "9F" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                val a = getLength(lenCode)
                getTlV(newData, a, 2)
            }

            "DF" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                val a = getLength(lenCode)
                getTlV(newData, a, 2)
            }
            "7F" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                getTLVClass(newData, lenCode, 2)
            }
            "BF" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                val a = getLength(lenCode)
                getTlV(data, a, 2)
            }
            "FF" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                val a = getLength(lenCode)
                getTlV(data, a, 2)
            }
            "5F" -> {
                val newData = data.substring(i, data.length)
                val lenCode = newData.substring(2, 4)
                val a = getLength(lenCode)
                getTlV(data, a, 2)
            }

            else -> {
                val newData = data.substring(i, data.length)
                parseTLV(newData)
            }
        }
        tlvs.add(tagState)
        val dataState = "${tagState.tag}${tagState.length}${tagState.value}"
        println("xxxxxxxx $dataState")
        i += dataState.length


    }

    return tlvs
}

fun parseTLV(tlvData: String): TLV {
    var i = 0
    val tag = tlvData.substring(i, i + 2)
    i += 2
    val lengthHex = tlvData.substring(i, i + 2)
    val length = lengthHex.toInt(16)
    i += 2
    val value = tlvData.substring(i, i + length * 2)
    i += length * 2
    return TLV(tag, lengthHex, value)

}
fun TestFormateTLV(hash: String): List<TLV> {
    return decodeTLVList(hash)
}
fun generateHashFromTLVList(tlvs: List<TLV>): String {
    val stringBuilder = StringBuilder()
    for (tlv in tlvs) {
        stringBuilder.append(tlv.tag)
        stringBuilder.append(tlv.length)
        stringBuilder.append(tlv.value)
    }
    return stringBuilder.toString()
}

