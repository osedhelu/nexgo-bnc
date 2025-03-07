package com.disglobal.bnc.data.remote.dto

import com.google.gson.annotations.SerializedName


data class LotSummaryResponse(

    @SerializedName("lotNumber") var lotNumber: Int? = null,
    @SerializedName("quantityRefund") var quantityRefund: Int? = null,
    @SerializedName("amountRefund") var amountRefund: String? = null,
    @SerializedName("quantitySales") var quantitySales: Int? = null,
    @SerializedName("amountSales") var amountSales: String? = null,
    @SerializedName("quantitySalesDebitApproved") var quantitySalesDebitApproved: Int? = null,
    @SerializedName("amountSalesDebitApproved") var amountSalesDebitApproved: String? = null,
    @SerializedName("quantitySalesCreditApproved") var quantitySalesCreditApproved: Int? = null,
    @SerializedName("amountSalesCreditApproved") var amountSalesCreditApproved: String? = null,
    @SerializedName("quantitySalesVMDebitApproved") var quantitySalesVMDebitApproved: Int? = null,
    @SerializedName("amountSalesVMDebitApproved") var amountSalesVMDebitApproved: String? = null

)