package com.disglobal.bnc.features.AnnularScreen.Tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disglobal.bnc.R
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers
import com.disglobal.bnc.ui.components.ButtonPersonal
import com.disglobal.bnc.ui.components.RowItems
import com.disglobal.bnc.utils.redidText

@Composable
fun AnnularTab03(onClick: () -> Unit) {
    val form by remember {
        com.disglobal.bnc.features.AnnularScreen.AnnulmentHelpers.formResp
    }
    val ctx = LocalContext.current
    val commerce = GetInfoAffiliatesResp.getCommerce(ctx)
    Text(text = "Detalles de la anulación", fontSize = 24.sp, fontWeight = FontWeight.Bold)

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
            RowItems(R.string.statuscode, "${form.statusCode}")
            RowItems(R.string.paymendetails, "${form.statusDescription}")
//      _____________________________________________________________________________________________
            RowItems(R.string.code_commerce, redidText("${commerce?.taxId}"))
//      _____________________________________________________________________________________________
            RowItems(R.string.code_terminal, redidText("${commerce?.serial}"))
            ButtonPersonal(title = "Finalizar", onClick = onClick)
        }
    }
}
