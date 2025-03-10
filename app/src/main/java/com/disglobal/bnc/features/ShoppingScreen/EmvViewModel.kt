package com.disglobal.bnc.features.ShoppingScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.utils.EmvCardReaderNew
import com.disglobal.bnc.utils.EmvCardReaderNew.TransactionState
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmvViewModel @Inject constructor(
    private val emvProcessor: EmvCardReaderNew,
    private val digipayRepository: DigipayRepository,
) : ViewModel(), EmvCardReaderNew.PinInputListener, EmvCardReaderNew.CardSelectionListener,
    EmvCardReaderNew.EmvProcessListener {
    private val TAG = "EmvViewModel"
    private val _passwordPIN = MutableLiveData("")
    fun passwordPIN(): LiveData<String> = _passwordPIN

    // LiveData para observar estados
    private val _transactionState = MutableLiveData<TransactionState>()
    fun transactionState(): LiveData<TransactionState> = _transactionState


    init {
        onListenerTransfer()
        onListenerPassword()
        emvProcessor.setPinInputListener(this)
        emvProcessor.setCardSelectionListener(this)
        emvProcessor.setEmvProcessListener(this)

    }

    private fun onListenerTransfer() {
        viewModelScope.launch {
            emvProcessor.transactionState.collect {
                _transactionState.postValue(it)
            }
        }
    }

    private fun onListenerPassword() {
        viewModelScope.launch {
            emvProcessor.passwordPIN.collect {
                _passwordPIN.postValue(it)
            }
        }
    }

    fun startEmvProcess(
        amountValue: String
    ) {
        status.value = ApiResponseStatus.Loading()
        emvProcessor.startEmvProcess(amountValue)
    }

    fun cancelEmvProcess() {
        emvProcessor.cancelEmvProcess()
    }

    fun onApplicationSelected(position: Int) {
        emvProcessor.onApplicationSelected(position)
    }

    fun onCardNumberConfirmed(confirmed: Boolean) {
        emvProcessor.onCardNumberConfirmed(confirmed)
    }

    fun validatePin(pin: String): Boolean {
        return emvProcessor.validatePin(pin)
    }

    fun onPinInputComplete(success: Boolean, isNoPin: Boolean) {
        emvProcessor.onPinInputComplete(success, isNoPin)
    }

    fun onPinInputCancelled() {
        emvProcessor.onPinInputCancelled()
    }

    // LiveData para la entrada de PIN
    val status: MutableState<ApiResponseStatus<String>> =
        mutableStateOf(ApiResponseStatus.Loading())    // Implementación de EmvViewModel.PinInputListener

    override fun onPinTextChanged(pinText: String) {
        // Esta función se maneja en la UI de Compose
    }

    override fun onPinInputComplete() {
        // Esta función se maneja en la UI de Compose
    }

    override fun onPinInputFailed(message: String) {
        status.value = ApiResponseStatus.Success(message)
    }

    // Implementación de EmvViewModel.CardSelectionListener
    override fun onCardSelectionRequired(
        appNameList: List<String>, appInfoList: List<CandidateAppInfoEntity>
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
        val resultMessage = when (resultCode) {
            // Códigos generales
            SdkResult.Success -> "Transacción exitosa"
            SdkResult.Fail -> "Operación fallida"
            SdkResult.Param_In_Invalid -> "Parámetro inválido"
            SdkResult.TimeOut -> "Tiempo de espera agotado"
            SdkResult.Device_Not_Ready -> "Dispositivo no está listo"
            SdkResult.NotSupport -> "Operación no soportada"
            SdkResult.Cancel -> "Operación cancelada"

            // Códigos de impresora
            SdkResult.Printer_Print_Fail -> "Error al imprimir"
            SdkResult.Printer_AddPrnStr_Fail -> "Error al agregar texto para imprimir"
            SdkResult.Printer_Busy -> "Impresora ocupada"
            SdkResult.Printer_PaperLack -> "Sin papel en la impresora"
            SdkResult.Printer_Fault -> "Falla en la impresora"
            SdkResult.Printer_TooHot -> "Impresora demasiado caliente"
            SdkResult.Printer_NoDevice -> "No se encontró dispositivo de impresión"

            // Códigos de escáner
            SdkResult.Scanner_Customer_Exit -> "Escaneo cancelado por usuario"
            SdkResult.Scanner_Other_Error -> "Error en el escáner"

            // Códigos de detección facial
            SdkResult.Face_Detect_Customer_Exit -> "Detección facial cancelada por usuario"
            SdkResult.Face_Detect_Call_Frequently -> "Llamadas frecuentes a detección facial"
            SdkResult.Face_Detect_Sdk_Not_Load -> "SDK de detección facial no cargado"
            SdkResult.Face_Detect_Service_Unbind -> "Servicio de detección facial desvinculado"
            SdkResult.Face_Detect_Other_Error -> "Error en detección facial"

            // Códigos de puerto serie
            SdkResult.SerialPort_Connect_Fail -> "Fallo de conexión de puerto serie"
            SdkResult.SerialPort_Send_Fail -> "Fallo al enviar datos por puerto serie"
            SdkResult.SerialPort_Port_Not_Open -> "Puerto serie no abierto"
            SdkResult.SerialPort_DisConnected -> "Puerto serie desconectado"

            // Códigos de lector de tarjetas magnéticas
            SdkResult.MagCardReader_NoPermission_Error -> "Sin permiso para lector de tarjetas magnéticas"
            SdkResult.MagCardReader_Other_Error -> "Error en lector de tarjetas magnéticas"

            // Códigos de lector de tarjetas ICC
            SdkResult.IccCardReader_Other_Error -> "Error en lector de tarjetas ICC"

            // Códigos de teclado PIN
            SdkResult.PinPad_No_Key_Error -> "No hay clave de PIN"
            SdkResult.PinPad_KeyIdx_Error -> "Error de índice de clave"
            SdkResult.PinPad_No_Pin_Input -> "No se ingresó PIN"
            SdkResult.PinPad_Input_Cancel -> "Ingreso de PIN cancelado"
            SdkResult.PinPad_Input_Timeout -> "Tiempo de espera de ingreso de PIN agotado"
            SdkResult.PinPad_Open_Or_Close_Error -> "Error al abrir/cerrar teclado PIN"

            // Códigos de EMV
            SdkResult.Emv_Other_Interface -> "Usar otra interfaz"
            SdkResult.Emv_Candidatelist_Empty -> "Lista de candidatos vacía"
            SdkResult.Emv_FallBack -> "Transacción EMV fallback"
            SdkResult.Emv_Qpboc_Offline -> "Transacción QPBOC offline"
            SdkResult.Emv_Qpboc_Online -> "Transacción QPBOC online"
            SdkResult.Emv_Offline_Accept -> "Transacción offline aceptada"
            SdkResult.Emv_Card_Removed -> "Tarjeta removida"
            SdkResult.Emv_Card_Block -> "Tarjeta bloqueada"
            SdkResult.Emv_App_Block -> "Aplicación bloqueada"
            SdkResult.Emv_App_Ineffect -> "Aplicación inefectiva"
            SdkResult.Emv_App_Expired -> "Aplicación expirada"
            SdkResult.Emv_Cancel -> "Transacción EMV cancelada"
            SdkResult.Emv_Declined -> "Transacción EMV rechazada"
            SdkResult.Emv_Offline_Declined -> "Transacción offline rechazada"
            SdkResult.Emv_Plz_See_Phone -> "Por favor, consulte el teléfono"
            SdkResult.Emv_Terminate -> "Transacción EMV terminada"
            SdkResult.Emv_USE_OTHER_CARD -> "Use otra tarjeta"
            SdkResult.Emv_CTLS_Select_App -> "Seleccione aplicación sin contacto"
            SdkResult.Emv_CTLS_EndApplication -> "Finalizar aplicación sin contacto"

            // Códigos de tarjeta ICC
            SdkResult.Icc_PullOut_Card -> "Tarjeta ICC retirada"
            SdkResult.Icc_Parity_Err -> "Error de paridad en tarjeta ICC"
            SdkResult.Icc_Channel_Err -> "Error de canal en tarjeta ICC"

            // Códigos de tarjeta PICC
            SdkResult.Picc_Not_Open -> "Lector de tarjeta PICC no abierto"
            SdkResult.Picc_Not_Searched_Card -> "No se encontró tarjeta PICC"
            SdkResult.Picc_Card_Too_Many -> "Demasiadas tarjetas PICC detectadas"

            // Códigos de tarjeta M1
            SdkResult.M1Card_Verify_Err -> "Error de verificación de tarjeta M1"

            // Códigos de plataforma
            SdkResult.Platform_Install_Already_Exists -> "Aplicación ya instalada"
            SdkResult.Platform_Install_Invalid_Apk -> "APK inválido"
            SdkResult.Platform_Install_Insufficient_Storage -> "Almacenamiento insuficiente"
            SdkResult.Platform_Uninstall_User_Restricted -> "Desinstalación restringida por usuario"

            // Otros códigos de EMV
            SdkResult.Emv_Communicate_Timeout -> "Tiempo de espera de comunicación EMV"
            SdkResult.Emv_CTLS_Torn -> "Transacción sin contacto incompleta"

            // Código de error genérico
            else -> "Código de resultado desconocido: $resultCode"
        }
        status.value = ApiResponseStatus.Success(resultMessage)
    }

    override fun onRequestShowToast(message: String) {
        status.value = ApiResponseStatus.Success(message)
    }


}