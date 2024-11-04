package com.osedhelu.bnc.data.remote.apiBanco


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osedhelu.bnc.data.remote.dto.ApiResponseStatus
import com.osedhelu.bnc.data.remote.dto.ReceiverStatusDTO
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiBancoViewModel @Inject constructor(
    private val repositoryApi: ApiBancoRepositoryImp,
) : ViewModel() {
    private val _status = MutableLiveData<ReceiverStatusDTO>()
    val status: LiveData<ReceiverStatusDTO> get() = _status

    fun getTestEcho() {
        viewModelScope.launch {
            handleGetStatusResponse(repositoryApi.receiverStatus())
        }

    }

    private fun handleGetStatusResponse(apiRespStatus: ApiResponseStatus<ReceiverStatusDTO>) {
        if (apiRespStatus is ApiResponseStatus.Success) {
            LoadingHelper.showLoadingSuccess("Success")
            _status.postValue(apiRespStatus.data)
        }
        if (apiRespStatus is ApiResponseStatus.Error) {
            LoadingHelper.showLoadingError("Error")
        }
    }


}

