package com.disglobal.bnc.ui.Screens.ShoppingScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.disglobal.bnc.utils.EmvCardReader
import com.disglobal.bnc.utils.EmvTransactionConfig
import com.nexgo.oaf.apiv3.SdkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// 1. ViewModel para manejar el estado EMV
class EmvViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val emvProcessor = EmvCardReader(context)
    private val _transactionState = MutableStateFlow<EmvState>(EmvState.Idle)
    val transactionState: StateFlow<EmvState> = _transactionState

    // En EmvViewModel.kt
    fun startTransaction(amount: Long, currency: String) {
        _transactionState.value = EmvState.Loading
        try {
            emvProcessor.setTransactionResultListener { resultCode, p1 ->
                _transactionState.value = when (resultCode) {
                    SdkResult.Success -> EmvState.Success // Usar constantes correctas del SDK
                    else -> EmvState.Error("Error código: $resultCode")
                }
            }

            // Configuración de prueba (usa datos reales en producción)
            val config = EmvTransactionConfig(
                amount = amount,
                currencyCode = currency,
                merchantId = "123456789012345", // Proporcionado por tu banco
                terminalId = "12345678",        // Proporcionado por tu banco
                transactionType = 0x00,         // 0x00 = Venta (byte, no String)
                countryCode = "484",            // Código de país para México
                isContactless = true
            )

//            emvProcessor.setContactlessMode()
            emvProcessor.startEmvTransaction(config)
//            emvProcessor.shutdown()
        } catch (e: Exception) {
            _transactionState.value = EmvState.Error(e.message ?: "Error desconocido")
        }
    }
}

// 2. Estados posibles
sealed class EmvState {
    object Idle : EmvState()
    object Loading : EmvState()
    object Success : EmvState()
    data class Error(val message: String) : EmvState()
}