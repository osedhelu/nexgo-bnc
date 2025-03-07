package com.disglobal.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReceiverStatusDTO(
    @SerializedName("message") var message: String? = null,
)
