package com.disglobal.bnc.features.TestConnectionScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDto
import com.disglobal.bnc.DigipayApi.domain.entities.EchoTestDtoResp
import com.disglobal.bnc.config.AFFILIATION_ID
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.features.TestConnectionScreen.widget.LoadingCustom
import com.disglobal.bnc.ui.components.StatusCustomScreen
import com.disglobal.bnc.utils.jsonParse


@Composable
fun TestConnectionScreen(
    viewModel: TestViewModel = hiltViewModel(), navController: NavController
) {
    val state by remember { viewModel.status }
    val iconLottie by remember { viewModel.typeLottie }
    val jsonLottieCompositionSpec: LottieCompositionSpec =
        LottieCompositionSpec.RawRes(iconLottie.rgb)
    val composition by rememberLottieComposition(jsonLottieCompositionSpec)
    LaunchedEffect(key1 = true) {
        viewModel.initEchoTest(
            EchoTestDto(
                AFFILIATION_ID, "1"
            )
        )
    }
    when (state) {
        is ApiResponseStatus.Loading -> {
            LoadingCustom(composition = composition)
        }

        is ApiResponseStatus.Error -> {
            val errorState = state as ApiResponseStatus.Error<EchoTestDtoResp>
            val message = jsonParse<EchoTestDtoResp>(errorState.message)
            StatusCustomScreen(
                ok = false,
                message = message.message ?: "",
                sudMessage = ""
            ) {
                navController.popBackStack()
            }
        }

        is ApiResponseStatus.Success -> {
            val successState = state as ApiResponseStatus.Success<EchoTestDtoResp>
            StatusCustomScreen(
                ok = true, message = successState.data.gestion ?: "", sudMessage = ""
            ) {
                navController.popBackStack()
            }
        }
    }
}
