package com.disglobal.bnc.ui.Screens.ShoppingScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs.PagoTab01
import com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs.PagoTab03
import com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs.PagoTab04
import com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs.ScreenPassword
import com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs.TypeMethodCard


@Composable
fun PagoScreen(navController: NavController, numTab: Int = 1) {
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
