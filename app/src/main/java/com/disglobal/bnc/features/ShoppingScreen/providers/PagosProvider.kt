package com.disglobal.bnc.features.ShoppingScreen.providers


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.ui.components.Loading.IconType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaymentProvider @Inject constructor(
    private val repositoryApi: DigipayRepository,
) : ViewModel() {
    val status: MutableState<ApiResponseStatus<String>> =
        mutableStateOf(ApiResponseStatus.Loading())
    val typeLottie: MutableState<IconType> = mutableStateOf(IconType.TEST)


    fun RegisterTransaction(
        hash: String,
    ) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            typeLottie.value = IconType.TEST
//            handleGetStatusResponse(
////                repositoryApi.registerTransaction(hash)
//            )
        }
    }


    private suspend fun handleGetStatusResponse(apiRespStatus: ApiResponseStatus<String>) {
        delay(1600)
        if (apiRespStatus is ApiResponseStatus.Success) {
            typeLottie.value = IconType.SUCCESS
        }
        if (apiRespStatus is ApiResponseStatus.Error) {
            typeLottie.value = IconType.DANGER
        }
        delay(1100)
        status.value = apiRespStatus
    }
}

