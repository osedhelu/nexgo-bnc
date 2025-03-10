package com.disglobal.bnc.features.ShoppingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.ui.theme.MyApplicationTheme
import com.disglobal.bnc.utils.EmvCardReaderNew

@Composable
fun EmvScreen(
    navController: NavController,
    viewModel: EmvViewModel = hiltViewModel()
) {
    val transactionState by viewModel.transactionState()
        .observeAsState(EmvCardReaderNew.TransactionState.Idle)
    val pinText by viewModel.passwordPIN().observeAsState("")

    val stateTransacion by viewModel.status

    var amount by remember { mutableStateOf("1.00") }
    var showAppSelectionDialog by remember { mutableStateOf(false) }
    var showCardConfirmationDialog by remember { mutableStateOf(false) }
    var showPinDialog by remember { mutableStateOf(false) }
    var appNames by remember { mutableStateOf(listOf<String>()) }
    var cardNumber by remember { mutableStateOf("") }


    // Observar cambios en el estado de la transacción
    LaunchedEffect(transactionState) {
        when (transactionState) {
            is EmvCardReaderNew.TransactionState.SelectApplication -> {
                appNames =
                    (transactionState as EmvCardReaderNew.TransactionState.SelectApplication).appNames
                showAppSelectionDialog = true
            }

            is EmvCardReaderNew.TransactionState.ConfirmCardNumber -> {
                cardNumber =
                    (transactionState as EmvCardReaderNew.TransactionState.ConfirmCardNumber).cardNumber
                showCardConfirmationDialog = true
            }

            is EmvCardReaderNew.TransactionState.PinRequested -> {
                showPinDialog = true
            }

            is EmvCardReaderNew.TransactionState.Completed -> {
                showAppSelectionDialog = false
                showCardConfirmationDialog = false
                showPinDialog = false
            }

            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Procesamiento de Pago EMV",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        when (stateTransacion) {
            is ApiResponseStatus.Error -> {
                val errorMessage = (stateTransacion as ApiResponseStatus.Error).message
                Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            }

            is ApiResponseStatus.Loading -> {}
            is ApiResponseStatus.Success -> {
                val message = (stateTransacion as ApiResponseStatus.Success).data
                Text(message, color = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Monto") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.startEmvProcess(amount.replace(".", "")) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Transacción")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.cancelEmvProcess() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Cancelar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Estado actual de la transacción
        when (transactionState) {
            is EmvCardReaderNew.TransactionState.Idle -> {
                Text("Listo para iniciar transacción")
            }

            is EmvCardReaderNew.TransactionState.CardReading -> {
                CircularProgressIndicator()
                Text("Esperando tarjeta...", style = MaterialTheme.typography.bodyLarge)
                Text(
                    "Por favor inserte o acerque su tarjeta al lector",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            is EmvCardReaderNew.TransactionState.ProcessingEmv -> {
                CircularProgressIndicator()
                Text("Procesando EMV...", style = MaterialTheme.typography.bodyLarge)
                Text("Por favor no retire la tarjeta", style = MaterialTheme.typography.bodyMedium)
            }

            is EmvCardReaderNew.TransactionState.ProcessingOnline -> {
                CircularProgressIndicator()
                Text("Procesando transacción online...", style = MaterialTheme.typography.bodyLarge)
                Text("Conectando con el servidor", style = MaterialTheme.typography.bodyMedium)
            }

            is EmvCardReaderNew.TransactionState.Error -> {
                val errorMessage =
                    (transactionState as EmvCardReaderNew.TransactionState.Error).message
                Text(
                    "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            is EmvCardReaderNew.TransactionState.TransactionCompleted -> {
                val resultCode =
                    (transactionState as EmvCardReaderNew.TransactionState.TransactionCompleted).resultCode
                Text(
                    "Transacción completada con código: $resultCode",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            else -> {}
        }
    }

    // Diálogo de selección de aplicación
    if (showAppSelectionDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Seleccione una aplicación") },
            text = {
                Column {
                    appNames.forEachIndexed { index, appName ->
                        Button(
                            onClick = {
                                viewModel.onApplicationSelected(index)
                                showAppSelectionDialog = false
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(appName)
                        }
                    }
                }
            },
            confirmButton = { }
        )
    }

    // Diálogo de confirmación de número de tarjeta
    if (showCardConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Confirmar número de tarjeta") },
            text = { Text("Número de tarjeta: $cardNumber") },
            confirmButton = {
                Button(onClick = {
                    viewModel.onCardNumberConfirmed(true)
                    showCardConfirmationDialog = false
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    viewModel.onCardNumberConfirmed(false)
                    showCardConfirmationDialog = false
                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo de ingreso de PIN
    if (showPinDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Ingrese su PIN") },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(pinText, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Use el teclado físico del dispositivo para ingresar su PIN")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (viewModel.validatePin(pinText)) {
                            viewModel.onPinInputComplete(true, false)
                            showPinDialog = false
                        }
                    }, enabled = pinText.length >= 4
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    viewModel.onPinInputCancelled()
                    showPinDialog = false
                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // No podemos previsualizar con un ViewModel real, así que usamos una versión simplificada
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Procesamiento de Pago EMV",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "1.00",
                onValueChange = { },
                label = { Text("Monto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Transacción")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cancelar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Listo para iniciar transacción")
        }
    }
}