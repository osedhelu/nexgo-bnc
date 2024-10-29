package com.osedhelu.bnc.ui.components.Loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun Loading(modifier: Modifier = Modifier) {
    val IconLottie by remember {
        LoadingHelper.iconSuccessError
    }
    val jsonLottieCompositionSpec: LottieCompositionSpec =
        LottieCompositionSpec.RawRes(IconLottie.rgb)
    val composition by rememberLottieComposition(jsonLottieCompositionSpec)
    val progress by animateLottieCompositionAsState(
        composition, iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .zIndex(1f), contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition, progress = progress, modifier = modifier
        )
    }
}

