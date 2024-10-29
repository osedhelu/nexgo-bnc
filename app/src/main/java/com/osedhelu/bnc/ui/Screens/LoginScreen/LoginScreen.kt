package com.osedhelu.bnc.ui.Screens.LoginScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osedhelu.bnc.R
import com.osedhelu.bnc.config.CommerceLocalData
import com.osedhelu.bnc.ui.Layouts.LoginLayout
import com.osedhelu.bnc.ui.components.ButtonPersonal
import com.osedhelu.bnc.ui.components.InputGlobal
import com.osedhelu.bnc.ui.components.Toast.ToastHelpers
import com.osedhelu.bnc.utils.ConvertToBase64
import com.osedhelu.bnc.utils.GoToActivity
import com.osedhelu.bnc.utils.iNameActivity

@Composable
fun LoginScreen() {
    val ctx = LocalContext.current
    val commerce = CommerceLocalData.getCommerce(ctx)
    println(commerce)
    var terminalCodeVisibility by rememberSaveable { mutableStateOf(false) }
    var terminalCode by rememberSaveable {
        mutableStateOf("")
    }
    var commerceCode by rememberSaveable {
        mutableStateOf("")
    }





    LoginLayout() {
        if (commerce == null) {
            Text(
                text = "Configurar Comercio", fontWeight = FontWeight.Bold, fontSize = 20.sp
            )
        } else {
            Text(text = "Iniciar Sesion", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.size(10.dp))

        InputGlobal(
            title = "Codigo de Comercio",
            value = commerceCode,
            isError = commerceCode.length <= 5,
            onChange = {
                commerceCode = it
            },
            trailingIcon = {},
            visualTransformation = VisualTransformation.None,
            messageError = "Debe tener mas de 6 caracteres"
        )
        Spacer(modifier = Modifier.size(10.dp))
        InputGlobal(
            title = "Codigo de Terminal",
            value = terminalCode,
            isError = terminalCode.length <= 5,
            onChange = {
                terminalCode = it
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
        Spacer(modifier = Modifier.size(30.dp))
        if (commerce != null) {
            ButtonPersonal(
                title = "Siguiente",
                enabled = terminalCode.length >= 6 && commerceCode.length >= 6,
                onClick = {
                    if (terminalCode == commerce.terminalCode && commerceCode == commerce.commerceCode) {
                        val token = ConvertToBase64("${commerceCode}${terminalCode}")
                        LoginHelpers.isLogin.value = true
                        CommerceLocalData.setTokenAuth(ctx, token)
                        ToastHelpers.show("Bienvenido")
                    } else {
                        ToastHelpers.show(
                            "Codigo Incorrecto",
                        )
                    }
                },
            )
        } else {
            ButtonPersonal(
                title = "Guardar Commerce",
                enabled = terminalCode.length >= 6 && commerceCode.length >= 6,
                onClick = {
                    CommerceLocalData.setCommerce(
                        ctx, CommerceLocalData(1, commerceCode, terminalCode, "", "", "")

                    )
                    GoToActivity(iNameActivity.MAIN, ctx, true)
                },
            )
        }
    }
}