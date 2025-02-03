package com.disglobal.bnc.tvldecoder.model

data class Tlv(
    val tag: String,
    val tagName: String,
    val value: String,
    val hexValueLength: Int
)