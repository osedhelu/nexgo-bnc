package com.disglobal.bnc.features.ReportScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

object ReportHelper : ViewModel() {

    val numTab: MutableState<Int> = mutableStateOf(0)

    @Synchronized
    fun reset() {
        numTab.value = 0
    }

    @Synchronized
    fun nextTabs() {
        numTab.value = numTab.value + 1
    }

    @Synchronized
    fun lastTabs() {
        numTab.value = numTab.value - 1
    }
}
