package com.osedhelu.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EchoTestDto(
    @SerializedName("affiliationId") var affiliationId: String? = null,
    @SerializedName("action") var action: String? = null
)

data class EchoTestDtoResp(
    @SerializedName("message") var message: String? = null
)