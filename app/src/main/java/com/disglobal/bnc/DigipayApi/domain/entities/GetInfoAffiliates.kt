package com.disglobal.bnc.DigipayApi.domain.entities

import android.content.Context
import com.google.gson.annotations.SerializedName

data class GetInfoAffiliatesResp(
    @SerializedName("id") var id: Int = 0,
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

) {

    companion object {
        private const val PRESF_SESSION = "GetInfoAffiliatesResp"
        private const val ID = "id_key"
        private const val MERCHANT = "merchant"
        private const val TERMINAL = "terminal"
        private const val LOT_NUMBER = "lotNumber"
        private const val STAN = "stan"
        private const val STATUS_ID = "statusId"
        private const val DESCRIPTION = "description"
        private const val COMMERCE_ID = "commerceId"
        private const val TAX_ID = "taxId"
        private const val NAME = "name"
        private const val ADDRESS = "address"
        private const val COMMERCE_STATUS_ID = "commerceStatusId"
        private const val DEVICE_ID = "deviceId"
        private const val SERIAL = "serial"
        private const val DEVICE_STATUS_ID = "deviceStatusId"
        private const val URL_BASE = "urlBase"
        private const val MK_KEY = "mkKey"
        private const val DUKPT_KEY = "dukptKey"
        private const val KSN = "ksn"
        private const val PROCESSOR_NAME = "processorName"
        private const val IP_ADDRESS = "ipAddress"
        private const val IP_PORT = "ipPort"
        private const val ACQUIRER_NAME = "acquirerName"
        private const val ACQUIRER_VALUE = "acquirerValue"
        private const val ACQUIRER_TAX_ID = "acquirerTaxId"
        private const val HEADER = "header"
        private const val UPDATED = "updated"
        private const val OPTIONAL = "optional"
        private const val CONTACTLESS_LIMIT = "contactlessLimit"
        private const val CVM_LIMIT = "cvmLimit"
        private const val CONFIG_PASSWORD = "configPassword"
        private const val REFUND_PASSWORD = "refundPassword"
        private const val CONFIG = "config"


        fun setCommerce(context: Context, commerce: GetInfoAffiliatesResp) {
            context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE).also {
                it.edit().putInt(ID, commerce.id ?: 0)
                    .putString(MERCHANT, commerce.merchant)
                    .putString(TERMINAL, commerce.terminal)
                    .putString(LOT_NUMBER, commerce.lotNumber)
                    .putString(STAN, commerce.stan)
                    .putInt(STATUS_ID, commerce.statusId ?: 0)
                    .putString(DESCRIPTION, commerce.description)
                    .putInt(COMMERCE_ID, commerce.commerceId ?: 0)
                    .putString(TAX_ID, commerce.taxId)
                    .putString(NAME, commerce.name)
                    .putString(ADDRESS, commerce.address)
                    .putInt(COMMERCE_STATUS_ID, commerce.commerceStatusId ?: 0)
                    .putInt(DEVICE_ID, commerce.deviceId ?: 0)
                    .putString(SERIAL, commerce.serial)
                    .putInt(DEVICE_STATUS_ID, commerce.deviceStatusId ?: 0)
                    .putString(URL_BASE, commerce.urlBase)
                    .putString(MK_KEY, commerce.mkKey)
                    .putString(DUKPT_KEY, commerce.dukptKey)
                    .putString(KSN, commerce.ksn)
                    .putString(PROCESSOR_NAME, commerce.processorName)
                    .putString(IP_ADDRESS, commerce.ipAddress)
                    .putString(IP_PORT, commerce.ipPort)
                    .putString(ACQUIRER_NAME, commerce.acquirerName)
                    .putString(ACQUIRER_VALUE, commerce.acquirerValue)
                    .putString(ACQUIRER_TAX_ID, commerce.acquirerTaxId)
                    .putString(HEADER, commerce.header)
                    .putBoolean(UPDATED, commerce.updated ?: false)
                    .putBoolean(OPTIONAL, commerce.optional ?: false)
                    .putString(CONTACTLESS_LIMIT, commerce.contactlessLimit)
                    .putString(CVM_LIMIT, commerce.cvmLimit)
                    .putString(CONFIG_PASSWORD, commerce.configPassword)
                    .putString(REFUND_PASSWORD, commerce.refundPassword)
                    .putString(CONFIG, commerce.config)
                    .apply()
            }
        }

        fun getCommerce(context: Context): GetInfoAffiliatesResp? {
            try {
                val prefs =
                    context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE) ?: return null
                val id_commerce = prefs.getInt(ID, 0)
                if (id_commerce === 0) {
                    return null
                } else {
                    return GetInfoAffiliatesResp(
                        id = prefs.getInt(ID, 0) ?: 0,
                        merchant = prefs.getString(MERCHANT, "") ?: "",
                        terminal = prefs.getString(TERMINAL, "") ?: "",
                        lotNumber = prefs.getString(LOT_NUMBER, "") ?: "",
                        stan = prefs.getString(STAN, "") ?: "",
                        statusId = prefs.getInt(STATUS_ID, 0) ?: 0,
                        description = prefs.getString(DESCRIPTION, "") ?: "",
                        commerceId = prefs.getInt(COMMERCE_ID, 0) ?: 0,
                        taxId = prefs.getString(TAX_ID, "") ?: "",
                        name = prefs.getString(NAME, "") ?: "",
                        address = prefs.getString(ADDRESS, "") ?: "",
                        commerceStatusId = prefs.getInt(COMMERCE_STATUS_ID, 0) ?: 0,
                        deviceId = prefs.getInt(DEVICE_ID, 0) ?: 0,
                        serial = prefs.getString(SERIAL, "") ?: "",
                        deviceStatusId = prefs.getInt(DEVICE_STATUS_ID, 0) ?: 0,
                        urlBase = prefs.getString(URL_BASE, "") ?: "",
                        mkKey = prefs.getString(MK_KEY, "") ?: "",
                        dukptKey = prefs.getString(DUKPT_KEY, "") ?: "",
                        ksn = prefs.getString(KSN, "") ?: "",
                        processorName = prefs.getString(PROCESSOR_NAME, "") ?: "",
                        ipAddress = prefs.getString(IP_ADDRESS, "") ?: "",
                        ipPort = prefs.getString(IP_PORT, "") ?: "",
                        acquirerName = prefs.getString(ACQUIRER_NAME, "") ?: "",
                        acquirerValue = prefs.getString(ACQUIRER_VALUE, "") ?: "",
                        acquirerTaxId = prefs.getString(ACQUIRER_TAX_ID, "") ?: "",
                        header = prefs.getString(HEADER, "") ?: "",
                        updated = prefs.getBoolean(UPDATED, false) ?: false,
                        optional = prefs.getBoolean(OPTIONAL, false) ?: false,
                        contactlessLimit = prefs.getString(CONTACTLESS_LIMIT, "") ?: "",
                        cvmLimit = prefs.getString(CVM_LIMIT, "") ?: "",
                        configPassword = prefs.getString(CONFIG_PASSWORD, "") ?: "",
                        refundPassword = prefs.getString(REFUND_PASSWORD, "") ?: "",
                        config = prefs.getString(CONFIG, "") ?: ""
                    )
                }
            } catch (e: Exception) {
                return null
            }
        }
    }
}
