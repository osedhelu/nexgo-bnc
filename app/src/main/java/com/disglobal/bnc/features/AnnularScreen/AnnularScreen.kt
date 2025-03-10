package com.disglobal.bnc.features.AnnularScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.disglobal.bnc.features.AnnularScreen.Tabs.AnnularTab02
import com.disglobal.bnc.features.AnnularScreen.Tabs.AnnularTab03
import com.disglobal.bnc.features.ShoppingScreen.tabs.TypeMethodCard
import com.disglobal.bnc.ui.components.Headers.HeaderHelper

@Composable
fun AnnularScreen(navController: NavController) {
    val numTab by remember {
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.numTab
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (numTab) {
//            0 -> AnnularTab01()
            0 -> {
                TypeMethodCard(navController = navController)
            }

            1 -> AnnularTab02(onClick = {
//                viewModel.AnnulmentPayment("${token}") {
//                    lifecycleOwner.lifecycleScope.launch {
//                        withContext(Dispatchers.Main) {
//                            AnnulmentHelpers.nextTabs()
//                            LoadingHelper.ResetParams()
//                        }
//                    }
//                }
            })

            2 -> AnnularTab03 {
                HeaderHelper.reset()
                navController.popBackStack()
            }
        }
    }
}


