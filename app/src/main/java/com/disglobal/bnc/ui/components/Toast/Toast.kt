package com.disglobal.bnc.ui.components.Toast

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun ToastComponent(
    duration: ToastDuration
) {
    val showDialog by remember { ToastHelpers.showToast }
    if (showDialog.showDialog) {
        AlertDialog(onDismissRequest = { ToastHelpers.deleteToast() },
            text = { Text(text = showDialog.title, fontSize = 20.sp) },
            confirmButton = {
                TextButton(onClick = { ToastHelpers.deleteToast() }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { ToastHelpers.deleteToast() }) {
                    Text(text = "CANCEL")
                }
            })

        LaunchedEffect(Unit) {
            delay(duration.time)
            ToastHelpers.deleteToast()
        }
    }
}


enum class ToastDuration(val time: kotlin.Long) {
    Short(1500L), Long(3000L)
}