package com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.disglobal.bnc.ui.theme.secondary

@Composable
fun PagoTab03(navController: NavController) {
    CardResponse(true) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.shadow(
                elevation = 8.dp,
                spotColor = Color(0xB906A4B4),
                ambientColor = Color(0xBE06A4B4)
            ),
            shape = RoundedCornerShape(12.dp) // Set corner radius to 0.dp for square edges

        ) {
            Text(text = "IMPRIMIR")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { navController.navigate("main") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    spotColor = secondary,
                    ambientColor = Color(0xBE06A4B4)
                ),
            shape = RoundedCornerShape(12.dp) // Set corner radius to 0.dp for square edges
        ) {
            Text(text = "Inicio")
        }
    }

}
