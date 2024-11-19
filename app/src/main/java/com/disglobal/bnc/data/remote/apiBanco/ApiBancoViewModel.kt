package com.disglobal.bnc.data.remote.apiBanco


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.data.remote.dto.ReceiverStatusDTO
import com.disglobal.bnc.ui.components.Loading.IconType
import com.disglobal.bnc.ui.components.Loading.LoadingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiBancoViewModel @Inject constructor(
    private val repositoryApi: ApiBancoRepositoryImp,
) : ViewModel() {



}

