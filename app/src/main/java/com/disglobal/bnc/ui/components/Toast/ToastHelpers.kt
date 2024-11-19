package com.disglobal.bnc.ui.components.Toast


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


data class ToastData(val title: String, val showDialog: Boolean)
object ToastHelpers : ViewModel() {
    val showToast: MutableState<ToastData> = mutableStateOf(ToastData("", false))

    @Synchronized
    fun deleteToast() {
        showToast.value = ToastData("", false)
    }

    @Synchronized
    fun show(text: String) {
        showToast.value = ToastData(text, true)
    }

}
