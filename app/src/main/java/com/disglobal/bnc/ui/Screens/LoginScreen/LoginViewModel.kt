package com.disglobal.bnc.ui.Screens.LoginScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.data.remote.apiBanco.ApiBancoRepositoryImp
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.data.remote.dto.GetInfoAffiliatesResp
import com.disglobal.bnc.ui.components.Loading.IconType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryApi: ApiBancoRepositoryImp,
) : ViewModel() {
    val status: MutableState<ApiResponseStatus<GetInfoAffiliatesResp>> =
        mutableStateOf(ApiResponseStatus.Success(GetInfoAffiliatesResp()))
    val typeLottie: MutableState<IconType> = mutableStateOf(IconType.TEST)


    fun getInfoClient(
        taxId: String,
        serial: String,
    ) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            typeLottie.value = IconType.TEST
            handleGetStatusResponse(
                repositoryApi.getInfoAffiliation(
                    taxId = taxId,
                    serial = serial,
                )
            )
        }
    }


    private suspend fun handleGetStatusResponse(apiRespStatus: ApiResponseStatus<GetInfoAffiliatesResp>) {
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

