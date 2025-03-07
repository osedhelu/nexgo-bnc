package com.disglobal.bnc.ui.test

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import com.disglobal.bnc.ui.theme.MyApplicationTheme
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.N)
@AndroidEntryPoint
class EmvActivity : ComponentActivity(), EmvViewModel.PinInputListener,
    EmvViewModel.CardSelectionListener, EmvViewModel.EmvProcessListener {

    private lateinit var viewModel: EmvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewModel usando Hilt
        viewModel = ViewModelProvider(this).get(EmvViewModel::class.java)

        // Configurar listeners
        viewModel.setPinInputListener(this)
        viewModel.setCardSelectionListener(this)
        viewModel.setEmvProcessListener(this)

        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    EmvScreen(viewModel)
                }
            }
        }
    }

    // Implementación de EmvViewModel.PinInputListener
    override fun onPinTextChanged(pinText: String) {
        // Esta función se maneja en la UI de Compose
    }

    override fun onPinInputComplete() {
        // Esta función se maneja en la UI de Compose
    }

    override fun onPinInputFailed(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    // Implementación de EmvViewModel.CardSelectionListener
    override fun onCardSelectionRequired(
        appNameList: List<String>,
        appInfoList: List<CandidateAppInfoEntity>
    ) {
        // Esta función se maneja en la UI de Compose
    }

    override fun onCardNumberConfirmation(cardNo: String) {
        // Esta función se maneja en la UI de Compose
    }

    // Implementación de EmvViewModel.EmvProcessListener
    override fun onEmvProcessStarted() {
        // Esta función se maneja en la UI de Compose
    }

    override fun onEmvProcessFinished(resultCode: Int) {
        runOnUiThread {
            val resultMessage = when (resultCode) {
                SdkResult.Success -> "Transacción exitosa"
                SdkResult.Emv_Qpboc_Offline, SdkResult.Emv_Offline_Accept -> "Transacción offline aprobada"
                SdkResult.Emv_Qpboc_Online -> "Transacción online requerida"
                SdkResult.Emv_Declined, SdkResult.Emv_Offline_Declined -> "Transacción rechazada"
                SdkResult.Emv_Cancel -> "Transacción cancelada"
                SdkResult.Emv_Card_Block -> "Tarjeta bloqueada"
                SdkResult.Emv_App_Block -> "Aplicación bloqueada"
                SdkResult.Emv_App_Ineffect -> "Aplicación inefectiva"
                SdkResult.Emv_App_Expired -> "Aplicación expirada"
                SdkResult.Emv_Other_Interface -> "Usar otra interfaz"
                SdkResult.Emv_Plz_See_Phone -> "Por favor vea el teléfono"
                else -> "Código de resultado: $resultCode"
            }
            Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestShowToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmvScreen(viewModel: EmvViewModel) {
    val context = LocalContext.current
    val transactionState by viewModel.transactionState()
        .observeAsState(EmvViewModel.TransactionState.Idle)

    var showPinDialog by remember { mutableStateOf(false) }

    var showAppSelectionDialog by remember { mutableStateOf(false) }
    var appNameList by remember { mutableStateOf<List<String>>(emptyList()) }

    var showCardConfirmDialog by remember { mutableStateOf(false) }
    var cardNumber by remember { mutableStateOf("") }

    var showAmountDialog by remember { mutableStateOf(false) }
    var amountInput by remember { mutableStateOf("") }

    // Nuevo estado para mostrar el diálogo de procesamiento en línea
    var showProcessingDialog by remember { mutableStateOf(false) }

    // Observar cambios en el estado de la transacción
    LaunchedEffect(transactionState) {
        when (transactionState) {
            is EmvViewModel.TransactionState.PinRequested -> {
                showPinDialog = true
            }

            is EmvViewModel.TransactionState.SelectApplication -> {
                appNameList =
                    (transactionState as EmvViewModel.TransactionState.SelectApplication).appNames
                showAppSelectionDialog = true
            }

            is EmvViewModel.TransactionState.ConfirmCardNumber -> {
                cardNumber =
                    (transactionState as EmvViewModel.TransactionState.ConfirmCardNumber).cardNumber
                showCardConfirmDialog = true
            }

            is EmvViewModel.TransactionState.ProcessingOnline -> {
                showProcessingDialog = true
            }

            is EmvViewModel.TransactionState.Completed,
            is EmvViewModel.TransactionState.Error -> {
                showProcessingDialog = false
                showPinDialog = false
            }

            else -> {}
        }
    }

    // Diálogo de PIN
    if (showPinDialog) {
        var pinInput by remember { mutableStateOf("") }
        val textPin by viewModel.passwordPIN().observeAsState("")

        Dialog(onDismissRequest = { }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 11.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$textPin",
                        style = MaterialTheme.typography.titleLarge
                    )

                    // Mostrar PIN enmascarado
                    Text(
                        text = "*".repeat(pinInput.length),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }

    // Diálogo de selección de aplicación
    if (showAppSelectionDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Seleccione una aplicación") },
            text = {
                LazyColumn {
                    items(appNameList.indices.toList()) { index ->
                        Button(
                            onClick = {
                                showAppSelectionDialog = false
                                viewModel.onApplicationSelected(index)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Text(appNameList[index])
                        }
                    }
                }
            },
            confirmButton = { }
        )
    }

    // Diálogo de confirmación de número de tarjeta
    if (showCardConfirmDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Confirme el número de tarjeta") },
            text = { Text(cardNumber) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showCardConfirmDialog = false
                        viewModel.onCardNumberConfirmed(true)
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCardConfirmDialog = false
                        viewModel.onCardNumberConfirmed(false)
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo para ingresar monto
    if (showAmountDialog) {
        val keyboardController =
            LocalSoftwareKeyboardController.current

        AlertDialog(
            onDismissRequest = { showAmountDialog = false },
            title = { Text("Ingrese el monto") },
            text = {
                TextField(
                    value = amountInput,
                    onValueChange = { amountInput = it },
                    label = { Text("Monto") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            // Opcionalmente, también puedes procesar el monto aquí
                            if (amountInput.isNotEmpty() && TextUtils.isDigitsOnly(amountInput)) {
                                showAmountDialog = false
                                viewModel.startEmvProcess(amountInput)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Ingrese un monto válido",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        keyboardController?.hide()
                        if (amountInput.isNotEmpty() && TextUtils.isDigitsOnly(amountInput)) {
                            showAmountDialog = false
                            viewModel.startEmvProcess(amountInput)
                        } else {
                            Toast.makeText(context, "Ingrese un monto válido", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        keyboardController?.hide()
                        showAmountDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Pantalla principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Estado actual de la transacción
        when (transactionState) {
            is EmvViewModel.TransactionState.CardReading,
            is EmvViewModel.TransactionState.ProcessingEmv -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                Text(
                    text = if (transactionState is EmvViewModel.TransactionState.CardReading)
                        "Leyendo tarjeta..."
                    else
                        "Procesando EMV...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {
                // Primera fila con botones Aid y Capk
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            val success = viewModel.initEmvAid()
                            Toast.makeText(
                                context,
                                if (success) "AID inicializado" else "Error al inicializar AID",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text(text = "Aid")
                    }

                    Button(
                        onClick = {
                            val success = viewModel.initEmvCapk()
                            Toast.makeText(
                                context,
                                if (success) "CAPK inicializado" else "Error al inicializar CAPK",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text(text = "Capk")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón aid_num
                Button(
                    onClick = {
                        val (aidNum, capkNum) = viewModel.checkAid()
                        Toast.makeText(
                            context,
                            "AID: $aidNum, CAPK: $capkNum",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "aid_num")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón aid_detail
                Button(
                    onClick = {
                        viewModel.checkAidDetail()
                        Toast.makeText(context, "Por favor revisa el Logcat", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "aid_detail")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón Start emv
                Button(
                    onClick = {
                        showAmountDialog = true
                        amountInput = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "Start emv")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón cancel emv
                Button(
                    onClick = {
                        viewModel.cancelEmvProcess()
                        Toast.makeText(context, "Proceso EMV cancelado", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "cancel emv")
                }
            }
        }
    }

    // Diálogo de procesamiento en línea
    if (showProcessingDialog) {
        Dialog(onDismissRequest = { /* No permitir cerrar al tocar fuera */ }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Procesando Pago",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Conectando con el servidor...",
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Por favor espere mientras procesamos su transacción",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(text = "Aid")
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(text = "Capk")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "aid_num")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "aid_detail")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Start emv")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "cancel emv")
            }
        }
    }
}