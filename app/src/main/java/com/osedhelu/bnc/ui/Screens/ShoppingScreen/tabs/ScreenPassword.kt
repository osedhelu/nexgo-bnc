package com.osedhelu.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.osedhelu.bnc.R
import com.osedhelu.bnc.ui.Layouts.LoginLayout
import com.osedhelu.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.osedhelu.bnc.ui.components.ButtonPersonal
import com.osedhelu.bnc.ui.components.InputGlobal

@Composable
fun ScreenPassword(navController: NavController) {
    var form by remember {
        PagoHelper.form
    }
    var terminalCodeVisibility by rememberSaveable { mutableStateOf(false) }
    LoginLayout() {
        InputGlobal(
            title = "PING",
            value = form.ping,
            isError = form.ping.length <= 5,
            onChange = {
                form = form.copy(
                    ping = it
                )
            },
            trailingIcon = {
                IconButton(onClick = { terminalCodeVisibility = !terminalCodeVisibility }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icons_eyes),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (terminalCodeVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            messageError = "Debe tener mas de 6 caracteres"
        )
        ButtonPersonal(
            title = "Iniciar Sesion",
            enabled = form.ping.length >= 6,
            onClick = {
                navController.navigate("compras/3")
            },
        )
    }
}
