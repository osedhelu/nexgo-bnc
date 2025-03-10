package com.disglobal.bnc.features.AnnularScreen

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
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.form.value = AnnulmentDto("", "")
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.numTab.value = 0
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.formResp.value = AnnulmentRespDto("", "")
    }

    fun nextTabs() {
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.numTab.value = com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.numTab.value + 1
    }

}
