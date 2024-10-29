package com.osedhelu.bnc.ui.components.Headers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


object HeaderHelper : ViewModel() {
    val btnVolver: MutableState<Boolean> = mutableStateOf(false)

    @Synchronized
    fun reset() {
        btnVolver.value = false
    }

    @Synchronized
    fun show() {
        btnVolver.value = true
    }

}
