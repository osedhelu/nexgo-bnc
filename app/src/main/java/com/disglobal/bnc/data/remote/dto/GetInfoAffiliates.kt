package com.disglobal.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GetInfoAffiliatesResp(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("merchant") var merchant: String? = null,
    @SerializedName("terminal") var terminal: String? = null,
    @SerializedName("lotNumber") var lotNumber: String? = null,
    @SerializedName("stan") var stan: String? = null,
    @SerializedName("statusId") var statusId: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("commerceId") var commerceId: Int? = null,
    @SerializedName("taxId") var taxId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("commerceStatusId") var commerceStatusId: Int? = null,
    @SerializedName("deviceId") var deviceId: Int? = null,
    @SerializedName("serial") var serial: String? = null,
    @SerializedName("deviceStatusId") var deviceStatusId: Int? = null,
    @SerializedName("urlBase") var urlBase: String? = null,
    @SerializedName("mkKey") var mkKey: String? = null,
    @SerializedName("dukptKey") var dukptKey: String? = null,
    @SerializedName("ksn") var ksn: String? = null,
    @SerializedName("processorName") var processorName: String? = null,
    @SerializedName("ipAddress") var ipAddress: String? = null,
    @SerializedName("ipPort") var ipPort: String? = null,
    @SerializedName("acquirerName") var acquirerName: String? = null,
    @SerializedName("acquirerValue") var acquirerValue: String? = null,
    @SerializedName("acquirerTaxId") var acquirerTaxId: String? = null,
    @SerializedName("header") var header: String? = null,
    @SerializedName("updated") var updated: Boolean? = null,
    @SerializedName("optional") var optional: Boolean? = null,
    @SerializedName("contactlessLimit") var contactlessLimit: String? = null,
    @SerializedName("cvmLimit") var cvmLimit: String? = null,
    @SerializedName("configPassword") var configPassword: String? = null,
    @SerializedName("refundPassword") var refundPassword: String? = null,
    @SerializedName("config") var config: String? = null

)
