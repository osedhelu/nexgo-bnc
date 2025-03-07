package com.disglobal.bnc.ui.test

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity

@Composable
fun EmvScreen(viewModel: EmvViewModel) {
    val transactionState by viewModel.transactionState().observeAsState(EmvViewModel.TransactionState.Idle)
    val pinText by viewModel.passwordPIN().observeAsState("")
    
    var amount by remember { mutableStateOf("1.00") }
    var showAppSelectionDialog by remember { mutableStateOf(false) }
    var showCardConfirmationDialog by remember { mutableStateOf(false) }
    var showPinDialog by remember { mutableStateOf(false) }
    var appNames by remember { mutableStateOf(listOf<String>()) }
    var cardNumber by remember { mutableStateOf("") }
    
    // Observar cambios en el estado de la transacción
    LaunchedEffect(transactionState) {
        when (transactionState) {
            is EmvViewModel.TransactionState.SelectApplication -> {
                appNames = (transactionState as EmvViewModel.TransactionState.SelectApplication).appNames
                showAppSelectionDialog = true
            }
            is EmvViewModel.TransactionState.ConfirmCardNumber -> {
                cardNumber = (transactionState as EmvViewModel.TransactionState.ConfirmCardNumber).cardNumber
                showCardConfirmationDialog = true
            }
            is EmvViewModel.TransactionState.PinRequested -> {
                showPinDialog = true
            }
            is EmvViewModel.TransactionState.Completed -> {
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
            text = "Procesamiento EMV",
            style = MaterialTheme.typography.headlineMedium
        )
        
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
        
        // Estado actual de la transacción
        when (transactionState) {
            is EmvViewModel.TransactionState.Idle -> {
                Text("Listo para iniciar transacción")
            }
            is EmvViewModel.TransactionState.CardReading -> {
                CircularProgressIndicator()
                Text("Esperando tarjeta...")
            }
            is EmvViewModel.TransactionState.ProcessingEmv -> {
                CircularProgressIndicator()
                Text("Procesando EMV...")
            }
            is EmvViewModel.TransactionState.ProcessingOnline -> {
                CircularProgressIndicator()
                Text("Procesando transacción online...")
            }
            is EmvViewModel.TransactionState.Error -> {
                val errorMessage = (transactionState as EmvViewModel.TransactionState.Error).message
                Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            }
            is EmvViewModel.TransactionState.TransactionCompleted -> {
                val resultCode = (transactionState as EmvViewModel.TransactionState.TransactionCompleted).resultCode
                Text("Transacción completada con código: $resultCode")
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
                            },
                            modifier = Modifier
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
                    Text(pinText)
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
                    },
                    enabled = pinText.length >= 4
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