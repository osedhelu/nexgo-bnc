package com.disglobal.bnc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.disglobal.bnc.config.FontSize

@Composable
fun RowItems(title: Int, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${stringResource(id = title)}: ",
            color = Color.Black,
            fontSize = FontSize.sp
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = FontSize.sp
        )
    }
}