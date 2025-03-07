package com.disglobal.bnc.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.ui.Layouts.MainLayout
import com.disglobal.bnc.ui.Screens.AnnularScreen.AnnularScreen
import com.disglobal.bnc.ui.Screens.LoginScreen.LoginScreen
import com.disglobal.bnc.ui.Screens.MainScreen.ReportScreen
import com.disglobal.bnc.ui.Screens.ProfileScreen.ProfileScreen
import com.disglobal.bnc.ui.Screens.ReportScreen.widget.ReportLayout
import com.disglobal.bnc.ui.Screens.ShoppingScreen.EmvScreen
import com.disglobal.bnc.ui.Screens.ShoppingScreen.PagoScreen
import com.disglobal.bnc.ui.Screens.TestConnectionScreen.TestConnectionScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val ctx = LocalContext.current
    val commerce = GetInfoAffiliatesResp.getCommerce(ctx)
    if (commerce !== null) {
//        val token = ConvertToBase64("${commerceId.commerceCode}${commerce.terminalCode}")
//        CommerceLocalData.setTokenAuth(ctx, token)    EmvCardReader(this).startEmvCardDetection()

//
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainLayout(navController = navController, logoCenter = true) {
//                    MainScreen(navController)
                    EmvScreen()
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
                    TestConnectionScreen(navController = navController)
                }
            }
        }
    } else {
        LoginScreen()
    }


}

