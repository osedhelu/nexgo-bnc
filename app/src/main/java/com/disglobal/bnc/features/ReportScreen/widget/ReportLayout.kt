package com.disglobal.bnc.features.ReportScreen.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.disglobal.bnc.ui.components.Loading.Loading
import com.disglobal.bnc.ui.components.Loading.LoadingHelper
import com.disglobal.bnc.ui.components.Toast.ToastComponent
import com.disglobal.bnc.ui.components.Toast.ToastDuration

@Composable
fun ReportLayout(
    navController: NavController,
    bottomBar: @Composable () -> Unit = { Spacer(modifier = Modifier.size(0.dp)) },
    children: @Composable () -> Unit
) {
    val LottieisLoadding by remember {
        LoadingHelper.isLoading
    }
    ToastComponent(
        ToastDuration.Long
    )
    if (LottieisLoadding) {
        Loading()
    } else {

        Scaffold(topBar = {
            HeaderReport(navController = navController)
        }, bottomBar = { bottomBar() }, containerColor = Color.White) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.TopCenter,

                ) {
                children()
            }

        }

    }
}
