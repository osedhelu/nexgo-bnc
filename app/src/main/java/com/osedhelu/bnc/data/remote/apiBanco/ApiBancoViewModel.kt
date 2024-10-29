package com.osedhelu.bnc.data.remote.apiBanco


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osedhelu.bnc.data.local.database.Transaction
import com.osedhelu.bnc.data.remote.ApiServiceInterceptor
import com.osedhelu.bnc.data.remote.dto.PaymentDto
import com.osedhelu.bnc.ui.Screens.AnnularScreen.AnnulmentHelpers
import com.osedhelu.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiBancoViewModel @Inject constructor(
    private val repositoryApi: ApiBancoRepositoryImp,
) : ViewModel() {

    val AllTransaccions: LiveData<List<Transaction>> by lazy {
        repositoryApi.getAllTransationsDB()
    }
    init {

        viewModelScope.launch(Dispatchers.IO) {
            repositoryApi.deleteInvoiceNotToday()
        }
    }

    fun AnnulmentPayment(token: String, callback: (status: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiServiceInterceptor.setBankOAuth2Token(token)
            LoadingHelper.showLoading("Anulando...")
            val respList = repositoryApi.AnnulmentPayment(AnnulmentHelpers.form.value)
            AnnulmentHelpers.formResp.value = respList.data
            delay(2000)
            if (respList.ok) {
                LoadingHelper.showLoadingSuccess("Anulación exitosa")
            } else {
                LoadingHelper.showLoadingError("Anulación fallida")
            }
            delay(1000)
            callback(respList.ok)
        }
    }

    fun emitPayment(body: PaymentDto, token: String, callback: (status: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            LoadingHelper.showLoading("Ejecutando...")
            ApiServiceInterceptor.setBankOAuth2Token(token)
            val respList = repositoryApi.emitPayment(body)
            PagoHelper.formResp.value = respList
            delay(2000)
            if (respList.ok) {
                LoadingHelper.showLoadingSuccess("Pago exitoso")
            } else {
                LoadingHelper.showLoadingError("Pago fallido")
            }

            PagoHelper.nextTabs()
            delay(1000)
            callback(respList.ok)

        }
    }
}