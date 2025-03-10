package com.disglobal.bnc.features.LoginScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


object LoginHelpers : ViewModel() {
    val isLogin: MutableState<Boolean> = mutableStateOf(false)

    @Synchronized
    fun reset() {
        isLogin.value = false
    }

}
