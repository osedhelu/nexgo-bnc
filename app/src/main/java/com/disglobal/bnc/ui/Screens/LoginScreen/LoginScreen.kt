package com.disglobal.bnc.ui.Screens.LoginScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.disglobal.bnc.R
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.data.remote.dto.GetInfoAffiliatesResp
import com.disglobal.bnc.ui.Layouts.LoginLayout
import com.disglobal.bnc.ui.components.ButtonPersonal
import com.disglobal.bnc.ui.components.InputGlobal
import com.disglobal.bnc.ui.components.Toast.ToastHelpers
import com.disglobal.bnc.utils.GoToActivity
import com.disglobal.bnc.utils.iNameActivity

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    val commerce = GetInfoAffiliatesResp.getCommerce(ctx)
    val state by remember { viewModel.status }
    var getInfoAffiliatesResp by remember { mutableStateOf(true) }
    var terminalCodeVisibility by rememberSaveable { mutableStateOf(false) }
    var taxId by rememberSaveable {
        mutableStateOf("J88888888888")
    }

    var serial by rememberSaveable {
        mutableStateOf("VPOS000888888888")
    }


    LoginLayout() {
        if (commerce == null) {
            Text(
                text = "Acceso", fontWeight = FontWeight.Bold, fontSize = 20.sp
            )
        } else {
            Text(text = "Iniciar Sesion", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.size(10.dp))

        Spacer(modifier = Modifier.size(10.dp))
        InputGlobal(
            title = "taxId",
            value = taxId,
            isError = taxId.length <= 5,
            onChange = {
                taxId = it
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
        InputGlobal(
            title = "Contraseña",
            value = serial,
            isError = serial.length <= 5,
            onChange = {
                serial = it
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
                enabled = taxId.length >= 6 && serial.length >= 6,
                onClick = {
                    GoToActivity(iNameActivity.MAIN, ctx)

                    if (taxId == commerce.taxId && serial == commerce.serial) {
//                        val token = ConvertToBase64("${commerceCode}${terminalCode}")
                        LoginHelpers.isLogin.value = true
//                        CommerceLocalData.setTokenAuth(ctx, token)
                        ToastHelpers.show("Bienvenido")
                    } else {
                        ToastHelpers.show(
                            "Codigo Incorrecto",
                        )
                    }
                },
            )
        } else {

            if (getInfoAffiliatesResp) {
                ButtonPersonal(
                    title = "Consultar Informacion",
                    enabled = taxId.length >= 6 && serial.length >= 6,
                    onClick = {
                        getInfoAffiliatesResp = false
                        viewModel.getInfoClient(taxId, serial)
                    },
                )
            } else {
                when (state) {
                    is ApiResponseStatus.Loading -> {
                        ButtonPersonal(title = "Cargando", enabled = false, onClick = {})
                    }

                    is ApiResponseStatus.Success -> {
                        ButtonPersonal(
                            title = "Guardar Informacion",
                            enabled = taxId.length >= 6 && serial.length >= 6,
                            onClick = {
//                            viewModel.getInfoClient(taxId, serial)
                                GetInfoAffiliatesResp.setCommerce(
                                    ctx,
                                    (state as ApiResponseStatus.Success<GetInfoAffiliatesResp>).data
                                )
                                GoToActivity(iNameActivity.MAIN, ctx)
                            },
                        )
                    }

                    is ApiResponseStatus.Error -> {
                        ButtonPersonal(title = "Volver", enabled = true, onClick = {
                            getInfoAffiliatesResp = true
                        })
                    }
                }

            }


        }
    }
}