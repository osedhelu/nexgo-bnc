package com.disglobal.bnc.ui.Screens.ReportScreen.tabs


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disglobal.bnc.data.local.database.Transaction


@Composable
fun ReportTab02(onClick: (Transaction) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth(0.9f), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "NEXGO")
            }
            Text(text = "NOMBRE COMERCIO:")


            RowItem("NEXGO:", "")
            RowItem("AFIL:", "0010000017")
            RowItem("LOTE CRÉDITO:", "000001")
            RowItem("LOTE DÉBITO:", "000001")
            RowItem("LOTE MASTER/VISA:", "000001")
            RowItem("LOTE EXTRAFINANCIAMIENTO:", "000001")
            RowItem("FECHA/HORA", "2024/10/03 07:46:38")
            Spacer(modifier = Modifier.size(10.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(1.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row3Item("TIPO", "Suma", "Monto")
            Row3Item("TARJETA CRÉDITO", "", "")
            Row3Item("COMPRA", "0", "Bs. 0.00")
            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
            Row3Item("TOTAL", "0", "Bs. 0.00")
            Row3Item("TARJETA DÉBITO", "", "")
            Row3Item("COMPRA", "0", "Bs. 0.00")
            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
            Row3Item("TOTAL", "0", "Bs. 0.00")
            Row3Item("DÉBITO MASTER/VISA", "", "")
            Row3Item("COMPRA", "0", "Bs. 0.00")
            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
            Row3Item("TOTAL", "0", "Bs. 0.00")
//            Row3Item("EXTRAFINANCIAMIENTO", "", "")
//            Row3Item("COMPRA", "0", "Bs. 0.00")
//            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
//            Row3Item("TOTAL", "0", "Bs. 0.00")
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(2.dp) // Set corner radius to 0.dp for square edges
            ) {
                Text(text = "IMPRIMIR")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun getViewReportTotal() {
    ReportTab02() {}
}