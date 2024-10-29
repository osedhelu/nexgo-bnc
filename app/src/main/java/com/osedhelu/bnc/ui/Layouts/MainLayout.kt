package com.osedhelu.bnc.ui.Layouts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.osedhelu.bnc.ui.components.Headers.MainHeader
import com.osedhelu.bnc.ui.components.Loading.Loading
import com.osedhelu.bnc.ui.components.Loading.LoadingHelper
import com.osedhelu.bnc.ui.components.Toast.ToastComponent
import com.osedhelu.bnc.ui.components.Toast.ToastDuration
import com.osedhelu.bnc.ui.components.sidebar.SidebarMain
import kotlinx.coroutines.launch


@Composable
fun MainLayout(
    navController: NavController,
    logoCenter: Boolean = false,
    bottomBar: @Composable () -> Unit = { Spacer(modifier = Modifier.size(0.dp)) },
    children: @Composable () -> Unit
) {
    val LottieisLoadding by remember {
        LoadingHelper.isLoading
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ToastComponent(
        ToastDuration.Long
    )
    if (LottieisLoadding) {
        Loading()
    } else {

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet { SidebarMain() }
            },
        ) {
            Scaffold(topBar = {
                MainHeader(navController = navController, btnVolver = !logoCenter, onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
            }, bottomBar = { bottomBar() }, containerColor = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.TopCenter,

                    ) {
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val boxSize = size
                        val borderF = 10f
                        val cornerRadius = CornerRadius(borderF, borderF)
                        val path = Path().apply {
                            addRoundRect(
                                RoundRect(
                                    rect = Rect(
                                        offset = Offset(0f, 0f),
                                        size = boxSize, //usar el tamaño de la caja
                                    ), topLeft = cornerRadius, topRight = cornerRadius
                                )
                            )
                        }
                        drawPath(path, color = Color.White)
                    }
                    children()
                }

            }

        }

    }
}
