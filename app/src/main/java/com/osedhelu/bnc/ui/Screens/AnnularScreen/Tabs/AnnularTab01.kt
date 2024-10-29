package com.osedhelu.bnc.ui.Screens.AnnularScreen.Tabs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osedhelu.bnc.R
import com.osedhelu.bnc.ui.Screens.AnnularScreen.AnnulmentHelpers
import com.osedhelu.bnc.ui.components.ButtonPersonal
import com.osedhelu.bnc.ui.components.InputGlobal

@Composable
fun AnnularTab01() {
    var form by remember {
        AnnulmentHelpers.form
    }
    Spacer(modifier = Modifier.size(8.dp))
    Text(
        text = stringResource(id = R.string.anular_pago),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    InputGlobal(
        title = stringResource(id = R.string.num_transaction),
        value = form.rrn,
        onChange = {
            form = form.copy(rrn = it)
        },
    )
    InputGlobal(
        title = stringResource(id = R.string.num_resibo),
        value = form.receiptId,
        onChange = {
            form = form.copy(receiptId = it)
        },
    )
    Spacer(modifier = Modifier.size(10.dp))
    ButtonPersonal(
        title = "Continuar",
        onClick = { AnnulmentHelpers.nextTabs() },
//        enabled = form.amount.isNotEmpty() && form.card.length == 16
    )
}