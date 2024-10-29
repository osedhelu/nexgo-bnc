package com.osedhelu.bnc.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.osedhelu.bnc.config.CommerceLocalData
import com.osedhelu.bnc.ui.Layouts.MainLayout
import com.osedhelu.bnc.ui.Screens.AnnularScreen.AnnularScreen
import com.osedhelu.bnc.ui.Screens.LoginScreen.LoginScreen
import com.osedhelu.bnc.ui.Screens.MainScreen.MainScreen
import com.osedhelu.bnc.ui.Screens.MainScreen.ReportScreen
import com.osedhelu.bnc.ui.Screens.ProfileScreen.ProfileScreen
import com.osedhelu.bnc.ui.Screens.ProfileScreen.TestConexionScreen
import com.osedhelu.bnc.ui.Screens.ReportScreen.widget.ReportLayout
import com.osedhelu.bnc.ui.Screens.ShoppingScreen.PagoScreen
import com.osedhelu.bnc.utils.ConvertToBase64

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val ctx = LocalContext.current
    val commerce = CommerceLocalData.getCommerce(ctx)

    if (commerce !== null) {
        val token = ConvertToBase64("${commerce.commerceCode}${commerce.terminalCode}")
        CommerceLocalData.setTokenAuth(ctx, token)
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainLayout(navController = navController, logoCenter = true) {
                    MainScreen(navController)
                }
            }
            composable("profile") {
                MainLayout(navController) {
                    ProfileScreen(navController)
                }
            }
            composable("compras") {
                MainLayout(navController) {
                    PagoScreen(navController)
                }
            }
            composable("compras/{num}") {
                val id = it.arguments?.getString("num")
                MainLayout(navController) {
                    PagoScreen(navController, id?.toInt() ?: 1)
                }
            }
            composable("anular") {

                MainLayout(navController) {
                    AnnularScreen(navController)
                }
            }
            composable("reportes") {
                ReportLayout(navController) {
                    ReportScreen(navController = navController)
                }
            }
            composable("reportes/1") {
                MainLayout(navController) {
                    ReportScreen(navController = navController, 1)
                }
            }
            composable("cierre") {
                MainLayout(navController) {
                    ReportScreen(navController = navController, 1)
                }
            }
            composable("test_conexion") {
                MainLayout(navController) {
                    TestConexionScreen(navController = navController)
                }
            }
        }
    } else {
        LoginScreen()
    }
}

