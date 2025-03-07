package com.disglobal.bnc.DigipayApi.domain.entities

import com.google.gson.annotations.SerializedName

data class ReceiverStatusDTO(
    @SerializedName("message") var message: String? = null,
)
