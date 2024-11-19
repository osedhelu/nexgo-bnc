package com.disglobal.bnc.ui.Screens.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.disglobal.bnc.R
import com.disglobal.bnc.ui.Screens.AnnularScreen.AnnulmentHelpers
import com.disglobal.bnc.ui.Screens.ReportScreen.ReportHelper
import com.disglobal.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.disglobal.bnc.ui.components.Headers.HeaderHelper
import com.disglobal.bnc.ui.theme.primary


data class iCardButton(val title: String, val icon: Int, val path: String)

@Composable
fun MainScreen(navController: NavController) {
    val itemsList: List<iCardButton> = listOf(
        iCardButton("TEST CONEXION", R.drawable.wifi, "test_conexion"),
        iCardButton("COMPRA", R.drawable.credit_card, "compras"),
        iCardButton("ANULACIÓN", R.drawable.relaod, "anular"),
        iCardButton("CIERRE", R.drawable.clipboard, "cierre"),
        iCardButton("REPORTE", R.drawable.report, "reportes"),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(
            start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(6.dp), // Espacio vertical entre elementos
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(itemsList) { item ->
            Button(modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp), // Ajusta el radio aquí
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                ),
                onClick = {
                    navController.navigate(item.path)
                    PagoHelper.reset()
                    AnnulmentHelpers.reset()
                    ReportHelper.reset()
                    HeaderHelper.show()
                }) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                        Modifier.size(50.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = item.title, fontSize = 8.sp, color = Color.White)
                }

            }


        }
    }
}