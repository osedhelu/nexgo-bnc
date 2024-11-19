package com.disglobal.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName


data class PaymentDto(

    @SerializedName("id") var id: String,
    @SerializedName("commerceCode") var commerceCode: String? = null,
    @SerializedName("terminalCode") var terminalCode: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("card") var card: String? = null

)

data class PaymentRespDto(
    @SerializedName("receiptId") var receiptId: String? = null,
    @SerializedName("rrn") var rrn: String? = null,
    @SerializedName("statusCode") var statusCode: String? = null,
    @SerializedName("statusDescription") var statusDescription: String? = null
)