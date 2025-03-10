package com.disglobal.bnc.ui.Screens.TestConnectionScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.ui.components.Loading.IconType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repositoryApi: DigipayRepository,
) : ViewModel() {
    val status: MutableState<ApiResponseStatus<EchoTestDtoResp>> =
        mutableStateOf(ApiResponseStatus.Loading())
    val typeLottie: MutableState<IconType> = mutableStateOf(IconType.TEST)
    fun initEchoTest(body: EchoTestDto) {
        viewModelScope.launch {
            typeLottie.value = IconType.TEST
            handleGetStatusResponse(repositoryApi.getInfoEchoTest(body))
        }
    }


    private suspend fun handleGetStatusResponse(apiRespStatus: ApiResponseStatus<EchoTestDtoResp>) {
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

