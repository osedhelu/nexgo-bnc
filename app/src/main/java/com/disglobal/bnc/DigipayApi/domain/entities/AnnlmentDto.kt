package com.disglobal.bnc.DigipayApi.domain.entities

import com.google.gson.annotations.SerializedName

data class AnnulmentDto(
    @SerializedName("receiptId")
    val receiptId: String,
    @SerializedName("rrn")
    val rrn: String
)

data class AnnulmentRespDto(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("statusDescription")
    val statusDescription: String
)