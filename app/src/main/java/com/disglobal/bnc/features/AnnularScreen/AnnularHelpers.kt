package com.disglobal.bnc.ui.Screens.AnnularScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.disglobal.bnc.DigipayApi.domain.entities.AnnulmentDto
import com.disglobal.bnc.DigipayApi.domain.entities.AnnulmentRespDto


object AnnulmentHelpers : ViewModel() {
    val form: MutableState<AnnulmentDto> = mutableStateOf(AnnulmentDto("", ""))
    val formResp: MutableState<AnnulmentRespDto> = mutableStateOf(AnnulmentRespDto("", ""))
    val numTab: MutableState<Int> = mutableStateOf(0)
    val showDialog: MutableState<Boolean> = mutableStateOf(false)

    @Synchronized
    fun reset() {
        form.value = AnnulmentDto("", "")
        numTab.value = 0
        formResp.value = AnnulmentRespDto("", "")
    }

    fun nextTabs() {
        numTab.value = numTab.value + 1
    }

}
