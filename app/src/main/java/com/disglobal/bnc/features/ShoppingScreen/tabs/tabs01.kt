package com.disglobal.bnc.features.ShoppingScreen.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.disglobal.bnc.R
import com.disglobal.bnc.features.ShoppingScreen.PagoHelper
import com.disglobal.bnc.ui.components.CustomKeyboard
import com.disglobal.bnc.utils.FormatePriceUtilString

@Composable
fun PagoTab01(
    navController: NavController,
) {
    val form by remember {
        PagoHelper.form
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.mensaje_compras))
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = FormatePriceUtilString(form.amount.value), fontSize = 20.sp)
        }
        CustomKeyboard(num = form.amount, onClick = {

            navController.navigate("compras/2")

        })

//        NumericKeyboard(
//            onNum = form.amount,
//            onEnter = {
//            },
//            onBack = { navController.popBackStack() }
//        )

    }
}