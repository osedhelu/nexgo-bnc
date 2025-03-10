package com.disglobal.bnc.ui.Screens.ShoppingScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.disglobal.bnc.DigipayApi.domain.entities.PaymentRespDto
import com.disglobal.bnc.data.remote.dto.ResponseDto
import com.disglobal.bnc.utils.convertirANumeroDecimal

data class PagoData(
    val card: String = "",
    var amount: MutableState<String> = mutableStateOf(""),
    val ping: String = ""
)

data class PagoDataResponse(val card: String, val amount: String)

object PagoHelper : ViewModel() {
    val form: MutableState<PagoData> = mutableStateOf(PagoData())
    val formResp: MutableState<ResponseDto<PaymentRespDto>> =
        mutableStateOf(ResponseDto(false, PaymentRespDto()))
    val numTab: MutableState<Int> = mutableStateOf(0)

    @Synchronized
    fun reset() {
        form.value = PagoData("", mutableStateOf(""))
        numTab.value = 0
    }

    @Synchronized
    fun nextTabs() {
        numTab.value = numTab.value + 1
    }

    @Synchronized
    fun lastTabs() {
        numTab.value = numTab.value - 1
    }

    @Synchronized
    fun ValidAmount(amount: String): Boolean {
        return convertirANumeroDecimal(amount) >= 0.01
    }

    fun validMaxAmount(numText: String): Boolean {
        val num = try {
            numText.toDouble()
        } catch (e: Exception) {
            0.00
        }
        return num >= 9000000000

    }
}

