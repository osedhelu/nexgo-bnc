package com.osedhelu.bnc.ui.Screens.TestConexionScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osedhelu.bnc.data.remote.apiBanco.ApiBancoRepositoryImp
import com.osedhelu.bnc.data.remote.dto.ApiResponseStatus
import com.osedhelu.bnc.data.remote.dto.ReceiverStatusDTO
import com.osedhelu.bnc.ui.components.Loading.IconType
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repositoryApi: ApiBancoRepositoryImp,
) : ViewModel() {
    private val _status = MutableLiveData<ReceiverStatusDTO>()
    val status: LiveData<ReceiverStatusDTO> get() = _status
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin
    val typeLottie: MutableState<IconType> = mutableStateOf(IconType.TEST)


    init {
        viewModelScope.launch {
            _isLogin.postValue(true)
            handleGetStatusResponse(repositoryApi.receiverStatus())
        }
    }


    private suspend fun handleGetStatusResponse(apiRespStatus: ApiResponseStatus<ReceiverStatusDTO>) {
        delay(1600)
        if (apiRespStatus is ApiResponseStatus.Success) {
            LoadingHelper.showLoadingSuccess("Success")
            typeLottie.value = IconType.SUCCESS
            _status.postValue(apiRespStatus.data)

        }
        if (apiRespStatus is ApiResponseStatus.Error) {
            LoadingHelper.showLoadingError("Error")
            typeLottie.value = IconType.DANGER
        }
        delay(1100)
        _isLogin.postValue(false)
    }


}

