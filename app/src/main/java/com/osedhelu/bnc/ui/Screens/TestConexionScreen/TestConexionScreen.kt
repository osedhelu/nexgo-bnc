package com.osedhelu.bnc.ui.Screens.ProfileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.osedhelu.bnc.ui.Screens.TestConexionScreen.TestViewModel
import com.osedhelu.bnc.ui.Screens.TestConexionScreen.widget.LoadingCustom


@Composable
fun TestConexionScreen(
    viewModel: TestViewModel = hiltViewModel(),
    navController: NavController
) {
    val isLoading by viewModel.isLogin.observeAsState(false)
    val state by viewModel.status.observeAsState()
    val iconLottie by remember { viewModel.typeLottie }
    val jsonLottieCompositionSpec: LottieCompositionSpec =
        LottieCompositionSpec.RawRes(iconLottie.rgb)
    val composition by rememberLottieComposition(jsonLottieCompositionSpec)
    if (isLoading) {
        LoadingCustom(composition = composition)
    } else {
        Column {
            Text(text = state?.message ?: "Error")
        }

    }
}