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
import androidx.compose.material3.Button
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
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0xB906A4B4),
                    ambientColor = Color(0xBE06A4B4)
                ),
            shape = RoundedCornerShape(12.dp) // Set corner radius to 0.dp for square edges
        ) {
            Text(text = "Inicio")
        }
    }

}
