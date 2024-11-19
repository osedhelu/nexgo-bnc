package com.osedhelu.bnc.ui.Screens.TestConnectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.osedhelu.bnc.config.AFFILIATION_ID
import com.osedhelu.bnc.data.remote.dto.ApiResponseStatus
import com.osedhelu.bnc.data.remote.dto.EchoTestDto
import com.osedhelu.bnc.data.remote.dto.EchoTestDtoResp
import com.osedhelu.bnc.ui.Screens.TestConnectionScreen.widget.LoadingCustom
import com.osedhelu.bnc.ui.components.StatusCustomScreen
import com.osedhelu.bnc.utils.jsonParse


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
