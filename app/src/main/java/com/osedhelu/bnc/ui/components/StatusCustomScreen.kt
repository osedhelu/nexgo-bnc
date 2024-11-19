package com.osedhelu.bnc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osedhelu.bnc.R
import com.osedhelu.bnc.ui.theme.danger
import com.osedhelu.bnc.ui.theme.success

@Composable
fun StatusCustomScreen(
    ok: Boolean?, message: String?, sudMessage: String?,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (ok == true) R.drawable.icon_success else R.drawable.icon_error),
            contentDescription = "No Internet Connection",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (ok == true) success else danger
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = sudMessage ?: "", fontSize = 16.sp, color = Color.Gray
        )
        if (onClick != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Volver")
            }
        }
    }
}
