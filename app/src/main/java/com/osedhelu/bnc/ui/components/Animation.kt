package com.osedhelu.bnc.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import java.util.Base64


@Composable
fun AnimationLayout(iterations: Int, children: @Composable () -> Unit) {
    var state by remember { mutableStateOf(true) }
    var animationFinished by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    if (alpha == 0f && !animationFinished) {
        animationFinished = true
        state = false
    }

    AnimatedVisibility(
        visible = state,
        modifier = Modifier.alpha(alpha)
    ) {
        children()
    }

    // Añade esto para detener la animación después de cierto número de repeticiones
    if (animationFinished && iterations == 0) {
        state = false
    }

}
