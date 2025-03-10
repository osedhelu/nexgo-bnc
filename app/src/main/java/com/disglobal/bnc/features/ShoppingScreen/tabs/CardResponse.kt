package com.disglobal.bnc.features.ShoppingScreen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disglobal.bnc.R
import com.disglobal.bnc.features.ReportScreen.tabs.RowItem

@Composable
fun CardResponse(status: Boolean = false, ButtonContainer: @Composable () -> Unit = {}) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth(0.9f), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = if (status) R.drawable.icon_success else R.drawable.icon_error),
                contentDescription = if (status) "Success" else "Error",
                tint = Color.Unspecified,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "NEXGO", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Debit Mastercard",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            RowItem("NOMBRE COMERCIO:", "")
            RowItem("NEXGO:", fontWeight = FontWeight.Bold, value = "")
            RowItem("AFIL:", "0010000017")
            RowItem("TARJETA:", "")
            RowItem("234234***323424 (C)", value = "", fontWeight = FontWeight.Bold)
            RowItem("TIPO TRANS:", "000001")
            RowItem("Compra", "", fontWeight = FontWeight.Bold)
            RowItem("LOTE:", "000001")
            RowItem("TRANCE:", "000013")
            RowItem("ABROB:", "")
            RowItem("REF:", "00001")
            RowItem("FECHA/HORA", "2024/10/03 07:46:38")
            RowItem("", "Bs. 9.00", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.size(10.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(1.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))



            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ButtonContainer()
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardResponse() {
    CardResponse(true) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(2.dp) // Set corner radius to 0.dp for square edges
        ) {
            Text(text = "IMPRIMIR")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(2.dp) // Set corner radius to 0.dp for square edges
        ) {
            Text(text = "Inicio")
        }
    }
}