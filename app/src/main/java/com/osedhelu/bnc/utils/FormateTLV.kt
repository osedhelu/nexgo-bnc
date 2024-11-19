package com.credibanco.smartpos.payment.utils

data class TLV(val tag: String, val length: String, val value: String)

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
        val tagState: TLV =
                when (label) {
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
                    else -> {
                        val newData = data.substring(i, data.length)
                        val lenCode = newData.substring(2, 4)
                        val a = getLength(lenCode)
                        getTlV(data, a, 2)
                    }
                }
        tlvs.add(tagState)
        val dataState = "${tagState.tag}${tagState.length}${tagState.value}"
        println("xxxxxxxx $dataState")
        i += dataState.length
    }

    return tlvs
}

fun TestFormateTLV(hash: String): List<TLV> {
    return decodeTLVList(hash)
}
