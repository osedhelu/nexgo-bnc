package com.osedhelu.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EchoTestDto(
    @SerializedName("affiliationId") var affiliationId: String? = null,
    @SerializedName("action") var action: String? = null
)

data class EchoTestDtoResp(
    @SerializedName("message") var message: String? = null,
    @SerializedName("gestion") var gestion: String? = null,
    @SerializedName("tmpAtention") var tmpAtention: String? = null,
    @SerializedName("CVMLimit") var CVMLimit: String? = null,
    @SerializedName("contactlessLimit") var contactlessLimit: String? = null,
    @SerializedName("receipt") var receipt: String? = null
)