package com.disglobal.bnc.ui.Screens.MainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.disglobal.bnc.ui.Screens.ReportScreen.tabs.ReportTab01
import com.disglobal.bnc.ui.Screens.ReportScreen.tabs.ReportTab02


@Composable
fun ReportScreen(navController: NavController, numTab: Int = 0) {

    when (numTab) {
        0 -> ReportTab01 {}
        1 -> {
            ReportTab02 {}
        }
    }

}
