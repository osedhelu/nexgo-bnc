package com.osedhelu.bnc.ui.Screens.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.osedhelu.bnc.ui.components.Loading.IconType
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper.isLoading
import kotlinx.coroutines.delay


@Composable
fun TestConexionScreen(navController: NavController) {
    var iconLottie by remember {
        LoadingHelper.iconSuccessError
    }
    LaunchedEffect(Unit) {
        iconLottie = IconType.TEST
        delay(1600)
        iconLottie = IconType.DANGER
        delay(1300)
        LoadingHelper.ResetParams()
        navController.popBackStack()
    }
    val jsonLottieCompositionSpec: LottieCompositionSpec =
        LottieCompositionSpec.RawRes(iconLottie.rgb)
    val composition by rememberLottieComposition(jsonLottieCompositionSpec)
    val progress by animateLottieCompositionAsState(
        composition, iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition, progress = progress, modifier = Modifier.fillMaxWidth()
        )
    }
}