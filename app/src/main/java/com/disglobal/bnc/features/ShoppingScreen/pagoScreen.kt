package com.disglobal.bnc.features.ShoppingScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.disglobal.bnc.features.ShoppingScreen.tabs.PagoTab01
import com.disglobal.bnc.features.ShoppingScreen.tabs.PagoTab03
import com.disglobal.bnc.features.ShoppingScreen.tabs.PagoTab04
import com.disglobal.bnc.features.ShoppingScreen.tabs.ScreenPassword
import com.disglobal.bnc.features.ShoppingScreen.tabs.TypeMethodCard
import com.disglobal.bnc.ui.test.EmvViewModel


@Composable
fun PagoScreen(navController: NavController, numTab: Int = 1, viewModelNexgo: EmvViewModel) {
    when (numTab) {
        1 -> {
            PagoTab01(navController = navController)
        }

        2 -> {
            TypeMethodCard(navController = navController)
        }

        3 -> {
            PagoTab03(navController = navController)
        }

        4 -> {
            ScreenPassword(navController = navController)
        }

        else -> {
            PagoTab04(navController = navController)
        }
    }

}
