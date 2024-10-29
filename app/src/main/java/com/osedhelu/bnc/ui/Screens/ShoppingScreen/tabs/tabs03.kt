package com.osedhelu.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.osedhelu.bnc.R
import com.osedhelu.bnc.config.CommerceLocalData
import com.osedhelu.bnc.config.FontSize
import com.osedhelu.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.osedhelu.bnc.ui.components.ButtonPersonal
import com.osedhelu.bnc.ui.theme.Purple40
import com.osedhelu.bnc.utils.FormatePriceUtils
import com.osedhelu.bnc.utils.redidText

@Composable
fun PagoTab03(navController: NavController) {
    val form by remember {
        PagoHelper.form
    }
    val ctx = LocalContext.current
    val commerce = CommerceLocalData.getCommerce(ctx)
    Text(text = "Hola Factura", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.size(33.dp))
    Box(
        modifier = Modifier
            .shadow(
                elevation = 8.dp, spotColor = Purple40, ambientColor = Purple40
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
    ) {

        Column(
            Modifier
                .padding(10.dp), verticalArrangement = Arrangement.spacedBy(
                space = 13.dp, // espacio entre los elementos
                alignment = Alignment.Top
            )
        ) {

//      _____________________________________________________________________________________________

//      _____________________________________________________________________________________________
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.credit_card),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
                Text(
                    text = redidText(form.card),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
            }

//      _____________________________________________________________________________________________
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.code_commerce),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
                Text(
                    text = redidText("${commerce?.commerceCode}"),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
            }


//      _____________________________________________________________________________________________
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.code_terminal),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
                Text(
                    text = redidText("${commerce?.terminalCode}"),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.monto),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
                Text(
                    text = FormatePriceUtils(form.amount.value),
                    color = Color.Black,
                    fontSize = FontSize.sp
                )
            }

            ButtonPersonal(title = "Confirmar", onClick = {

            })

        }
    }
}
