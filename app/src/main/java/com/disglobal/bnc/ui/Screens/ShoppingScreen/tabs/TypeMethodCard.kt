package com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.disglobal.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.disglobal.bnc.ui.Screens.ShoppingScreen.providers.PaymentProvider
import com.disglobal.bnc.ui.components.Loading.IconType
import com.disglobal.bnc.utils.convertirANumeroDecimal
import kotlinx.coroutines.delay

@Composable
fun TypeMethodCard(
    navController: NavController,
) {
    val form by remember {
        PagoHelper.form
    }

    val amountText by remember {
        form.amount
    }
    val amount = convertirANumeroDecimal(amountText)
    val jsonLottieCompositionSpec: LottieCompositionSpec =
        LottieCompositionSpec.RawRes(if (amount > 500) IconType.CARD_CONTACT.rgb else IconType.CARD_NFC.rgb)
    val composition by rememberLottieComposition(jsonLottieCompositionSpec)
    val progress by animateLottieCompositionAsState(
        composition, iterations = LottieConstants.IterateForever
    )
    LaunchedEffect(true) {

        delay(1000L) // 20 seconds delay

        if (amount > 20) {
            navController.navigate("compras/4") // Replace with the actual route of the next screen
        } else {
            navController.navigate("compras/3") // Replace with the actual route of the next screen
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (amount > 500) "Por favor, pase la tarjeta haciendo contacto con el dispositivo."
                else "Puede leer la tarjeta por contacto o sin contacto.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            LottieAnimation(
                composition = composition, progress = progress,
            )
        }
    }
}
