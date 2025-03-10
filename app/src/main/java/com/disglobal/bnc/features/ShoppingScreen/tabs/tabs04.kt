package com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.disglobal.bnc.R
import com.disglobal.bnc.config.FontSize
import com.disglobal.bnc.DigipayApi.domain.entities.AnnulmentDto
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.ui.Screens.AnnularScreen.AnnulmentHelpers
import com.disglobal.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.disglobal.bnc.ui.components.ButtonPersonal
import com.disglobal.bnc.ui.components.Toast.ToastHelpers
import com.disglobal.bnc.utils.FormatePriceUtils
import com.disglobal.bnc.utils.copyToClipboard
import com.disglobal.bnc.utils.redidText

@Composable
fun PagoTab04(navController: NavController) {
    val form by remember {
        PagoHelper.form
    }
    val formResp by remember {
        PagoHelper.formResp
    }
    val ctx = LocalContext.current
    val commerce = GetInfoAffiliatesResp.getCommerce(ctx)
    Text(text = "C", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.size(33.dp))
    Box(
        modifier = Modifier
            .shadow(
                elevation = 8.dp, spotColor = Color(0xB906A4B4), ambientColor = Color(0xBE06A4B4)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
    ) {

        Column(
            Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(
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
                    fontSize = (FontSize - 3).sp
                )
                Text(
                    text = redidText(form.card), color = Color.Black, fontSize = (FontSize - 3).sp
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
                    fontSize = (FontSize - 3).sp
                )
                Text(
                    text = redidText("${commerce?.taxId}"),
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
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
                    fontSize = (FontSize - 3).sp
                )
                Text(
                    text = redidText("${commerce?.serial}"),
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.monto),
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
                )
                Text(
                    text = FormatePriceUtils(form.amount.value),
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.paymendetails),
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
                )
                Text(
                    text = "${formResp.data.statusDescription}",
                    color = Color.Black,
                    fontSize = (FontSize - 3).sp
                )
            }
            if (formResp.ok) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.num_transaction),
                        color = Color.Black,
                        fontSize = (FontSize - 3).sp
                    )
                    Text(

                        modifier = Modifier.clickable {
                            copyToClipboard(ctx, "${formResp.data.rrn}")
                            ToastHelpers.show("Copiar el número de transacción: ${formResp.data.rrn}")

                        },
                        text = "${formResp.data.rrn?.let { redidText(it) }}",
                        color = Color.Black,
                        fontSize = (FontSize - 3).sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.num_resibo),
                        color = Color.Black,
                        fontSize = (FontSize - 3).sp
                    )
                    Text(
                        modifier = Modifier.clickable {
                            copyToClipboard(ctx, "${formResp.data.receiptId}")
                            ToastHelpers.show("Número de recibo: ${formResp.data.rrn}")

                        },
                        text = "${formResp.data.receiptId?.let { redidText(it) }}",
                        color = Color.Black,
                        fontSize = (FontSize - 3).sp
                    )
                }
            }

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = if (formResp.ok) R.drawable.icon_success else R.drawable.icon_error),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(50.dp),
                    contentDescription = ""
                )

            }
            ButtonPersonal(title = "Finalizar", onClick = {
                navController.navigate("main")
            })
            if (formResp.ok) {
                ButtonPersonal(title = "Anular transferencia", onClick = {
                    AnnulmentHelpers.form.value = AnnulmentDto(
                        receiptId = "${formResp.data.receiptId}", rrn = "${formResp.data.rrn}"
                    )
                    PagoHelper.nextTabs()

                })

            }
            Spacer(modifier = Modifier.size(10.dp))


        }
    }
}
