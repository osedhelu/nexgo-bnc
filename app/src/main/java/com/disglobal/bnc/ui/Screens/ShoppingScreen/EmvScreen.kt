package com.disglobal.bnc.ui.Screens.ShoppingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel


// 3. Pantalla en Compose
@Composable
fun EmvScreen() {
    val viewModel: EmvViewModel = viewModel()
    val state by viewModel.transactionState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.startTransaction(
                    amount = 1000L, // $10.00 en centavos
                    currency = "840" // USD
                )
            }, enabled = state !is EmvState.Loading
        ) {
            Text("Iniciar Transacción")
        }

        when (state) {
            is EmvState.Loading -> CircularProgressIndicator()
            is EmvState.Success -> Text("¡Transacción exitosa!", color = Color.Green)
            is EmvState.Error -> Text(
                text = (state as EmvState.Error).message, color = Color.Red
            )

            EmvState.Idle -> Unit
        }
    }
}
