package com.disglobal.bnc.utils


//
//data class TLV(
//    val tag: String,
//
//    val length: String,
//
//    val value: String
//)
//
//fun getLength(classValue: String): Int {
//    return when (classValue) {
//        "83" -> 8
//        else -> 6
//    }
//}
//
//
//fun getTlV(data: String, len: Int, lenCode: Int): TLV {
//    val TagName = data.substring(0, len)
//    val lenHex = TagName.substring(TagName.length - lenCode)
//    val value = data.substring(TagName.length, TagName.length + (lenHex.toInt(16) * 2))
//    return TLV(TagName.dropLast(2), lenHex, value)
//}
//
//fun getTLVClass(data: String, lenCode: String, len: Int): TLV {
//    val initTag = data.substring(0, 2) + data.substring(2, len)
//    val allTagAndValue = initTag + lenCode
//    val value =
//        data.substring(allTagAndValue.length, allTagAndValue.length + (lenCode.toInt(16) * 2))
//    return TLV(initTag, lenCode, value)
//}
//
//fun decodeTLVList(data: String): List<TLV> {
//    val tlvs = mutableListOf<TLV>()
//    var i = 0
//    var tmpIndex = 0
//
//    while (i < data.length) {
//        tmpIndex += 1
//        val label = data.substring(i, i + 2)
//        val newData = data.substring(i, data.length)
//        val tagState: TLV = when (label) {
//            "C0" -> {
//                val lenCode = newData.substring(2, 4)
//                getTLVClass(newData, lenCode, 2)
//            }
//
//            "C1" -> {
//                val lenCode = newData.substring(2, 4)
//                getTLVClass(newData, lenCode, 2)
//            }
//
//            "C2" -> {
//                val lenCode = newData.substring(4, 8)
//                getTLVClass(newData, lenCode, 4)
//            }
//
//            "C4" -> {
//                val lenCode = newData.substring(2, 4)
//                getTLVClass(newData, lenCode, 2)
//            }
//
//            "C5" -> {
//                val lenCode = newData.substring(2, 4)
//                getTLVClass(newData, lenCode, 2)
//            }
//
//            "DF" -> {
//                val lenCode = newData.substring(2, 4)
//                val a = getLength(lenCode)
//                getTlV(newData, a, 2)
//            }
//
//            "7F" -> {
//                val lenCode = newData.substring(2, 4)
//                getTLVClass(newData, lenCode, 2)
//            }
//
//            "BF" -> {
//                val lenCode = newData.substring(2, 4)
//                val a = getLength(lenCode)
//                getTlV(data, a, 2)
//            }
//
//            "FF" -> {
//                val lenCode = newData.substring(2, 4)
//                val a = getLength(lenCode)
//                getTlV(data, a, 2)
//            }
//
//            else -> {
//                parseTLV(newData)
//            }
//        }
//        tlvs.add(tagState)
//        val dataState = "${tagState.tag}${tagState.length}${tagState.value}"
//        i += dataState.length
//    }
//    return tlvs
//}
//
//
//fun main() {
//    val dataTLV =
//        decodeTLVList("5713377106286350004D100710107072317800002F5A08377106286350004F950500000000009A035001089B0200009C01005F201A54414E4720544149204B57414E204A494D4D59202020202020205F24031007105F2A0208405F300210109F02060000000012349F160F4D4F434B30303030303000000000009F1A0208409F1C0831313232333334349F1E0831323334353637389F21031936379F330360F8C89F3501219F370448C4ACC19F3901909F40057E0020B0019F41030003049F4E0D54657374204D65726368616E74DF8330020200")
//    println(jsonStringify(dataTLV))
//
//}