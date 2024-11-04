package com.osedhelu.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransacionDtoResp(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("timestamp") var timestamp: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null
)
