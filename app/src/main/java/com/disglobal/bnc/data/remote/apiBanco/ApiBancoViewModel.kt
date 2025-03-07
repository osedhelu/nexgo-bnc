package com.disglobal.bnc.data.remote.apiBanco


import androidx.lifecycle.ViewModel
import com.disglobal.bnc.DigipayApi.infrastructure.repositories.DigipayRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiBancoViewModel @Inject constructor(
    private val repositoryApi: DigipayRepositoryImp,
) : ViewModel() {


}

