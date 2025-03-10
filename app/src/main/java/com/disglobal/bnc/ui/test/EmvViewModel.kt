package com.disglobal.bnc.ui.test

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.features.common.emv.EmvRepository
import com.disglobal.bnc.nexgobnc
import com.google.gson.Gson
import com.nexgo.common.ByteUtils
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.pinpad.OnPinPadInputListener
import com.nexgo.oaf.apiv3.device.pinpad.PinAlgorithmModeEnum
import com.nexgo.oaf.apiv3.device.pinpad.PinKeyboardModeEnum
import com.nexgo.oaf.apiv3.device.pinpad.PinPadKeyCode
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import com.nexgo.oaf.apiv3.emv.EmvDataSourceEnum
import com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvOnlineResultEntity
import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
import com.nexgo.oaf.apiv3.emv.PromptEnum
import com.nexgo.oaf.apiv3.emv.UnionPayTransDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class EmvViewModel @Inject constructor(
    application: Application,
    private val emvRepository: EmvRepository,
    private val apiService: DigipayRepository
) : AndroidViewModel(application), OnCardInfoListener,
    OnEmvProcessListener2, OnPinPadInputListener {
    private val _passwordPIN = MutableLiveData("")
    fun passwordPIN(): LiveData<String> = _passwordPIN

    // LiveData para observar estados
    private val _transactionState = MutableLiveData<TransactionState>()
    fun transactionState(): LiveData<TransactionState> = _transactionState


    // Interfaces para comunicar eventos a la UI
    interface PinInputListener {
        fun onPinTextChanged(pinText: String)
        fun onPinInputComplete()
        fun onPinInputFailed(message: String)
    }

    // Método para manejar la entrada de PIN
    fun onPinInputComplete(success: Boolean, isNoPin: Boolean) {
        Log.d("nexgo", "onPinInputComplete: success=$success, isNoPin=$isNoPin")

        // Convertir el PIN a un arreglo de bytes si es necesario
        val pinData = if (success && !isNoPin) {
            // Aquí podrías convertir el PIN a un arreglo de bytes si es necesario
            // Por ejemplo, usando ByteUtils.hexString2ByteArray()
            null
        } else {
            null
        }

        // Llamar al método del SDK para procesar la entrada de PIN
        emvHandler2?.onSetPinInputResponse(
            success, isNoPin
        )
    }

    // Método para manejar la cancelación del PIN
    fun onPinInputCancelled() {
        Log.d("nexgo", "onPinInputCancelled")
        emvHandler2?.onSetPinInputResponse(false, false)
    }

    // Método para validar el PIN
    fun validatePin(pin: String): Boolean {
        // Validar que el PIN tenga entre 4 y 6 dígitos
        return pin.length in 4..6 && pin.all { it.isDigit() }
    }

    interface CardSelectionListener {
        fun onCardSelectionRequired(
            appNameList: List<String>, appInfoList: List<CandidateAppInfoEntity>
        )

        fun onCardNumberConfirmation(cardNo: String)
    }

    interface EmvProcessListener {
        fun onEmvProcessStarted()
        fun onEmvProcessFinished(resultCode: Int)
        fun onRequestShowToast(message: String)
    }

    // Variables para los listeners
    private var pinInputListener: PinInputListener? = null
    private var cardSelectionListener: CardSelectionListener? = null
    private var emvProcessListener: EmvProcessListener? = null

    // Componentes del SDK Nexgo
    private var deviceEngine: DeviceEngine? = null
    private var emvHandler2: EmvHandler2? = null
    private var emvUtils: EmvUtils? = null

    // Estado de la transacción
    private var cardNo: String? = null
    private var pwdText: String? = null
    private var amount: String? = null
    private var mExistSlot: CardSlotTypeEnum? = null
    private var isExpressPaySeePhoneTapCardAgain = false


    // Estados posibles de la transacción
    sealed class TransactionState {
        object Idle : TransactionState()
        object CardReading : TransactionState()
        object ProcessingEmv : TransactionState()
        object ProcessingOnline : TransactionState()
        object PinRequested : TransactionState()
        data class SelectApplication(val appNames: List<String>) : TransactionState()
        data class ConfirmCardNumber(val cardNumber: String) : TransactionState()
        data class TransactionCompleted(val resultCode: Int) : TransactionState()
        data class Error(val message: String) : TransactionState()
        object Completed : TransactionState()
    }

    init {
        // Inicializar componentes
        val app = getApplication<nexgobnc>()
        deviceEngine = app.deviceEngine
        emvHandler2 = deviceEngine?.getEmvHandler2("app2")

        // Habilitar logs
        emvHandler2?.emvDebugLog(true)
        LogUtils.setDebugEnable(true)

        // Inicializar EmvUtils
        emvUtils = EmvUtils.build(app)

        // Estado inicial
        _transactionState.value = TransactionState.Idle
    }

    // Métodos para establecer listeners
    fun setPinInputListener(listener: PinInputListener) {
        pinInputListener = listener
    }

    fun setCardSelectionListener(listener: CardSelectionListener) {
        cardSelectionListener = listener
    }

    fun setEmvProcessListener(listener: EmvProcessListener) {
        emvProcessListener = listener
    }

    // Métodos públicos para interactuar con el ViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    fun initEmvAid(): Boolean {
        return emvRepository.initEmvAid()
    }

    fun initEmvCapk(): Boolean {
        return emvRepository.initEmvCapk()
    }

    fun checkAid(): Pair<Int, Int> {
        return emvRepository.checkAid()
    }

    fun checkAidDetail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val aidEntities = emvHandler2?.aidList
                if (aidEntities == null) {
                    Log.d("nexgo", "AID = null")
                } else {
                    for (aidEntity in aidEntities) {
                        Log.d("nexgo", "AID = " + Gson().toJson(aidEntity))
                    }
                }

                val capkEntities = emvHandler2?.capkList
                if (capkEntities == null) {
                    Log.d("nexgo", "capkEntities = null")
                } else {
                    for (capkEntity in capkEntities) {
                        Log.d("nexgo", "CAPK = " + Gson().toJson(capkEntity))
                    }
                }
            }
        }
    }

    fun startEmvProcess(amountValue: String) {
        amount = leftPad(amountValue, 12, '0')
        Log.d("nexgo", "amount = $amount")
        startEmvTest()
    }

    fun cancelEmvProcess() {
        emvHandler2?.emvProcessCancel()
    }

    // Métodos para responder a eventos de la UI

    fun onApplicationSelected(position: Int) {
        emvHandler2?.onSetSelAppResponse(position + 1)
    }

    fun onCardNumberConfirmed(confirmed: Boolean) {
        emvHandler2?.onSetConfirmCardNoResponse(confirmed)
    }


    // Implementaciones de los listeners del SDK

    private fun startEmvTest() {
        // Verificar que el motor del dispositivo y el lector de tartas estén disponibles
        val deviceEngine = deviceEngine
        if (deviceEngine == null) {
            Log.e("nexgo", "Device engine is null")
            _transactionState.postValue(TransactionState.Error("Motor del dispositivo no disponible"))
            emvProcessListener?.onRequestShowToast("Error: Motor del dispositivo no disponible")
            return
        }

        val cardReader = deviceEngine.cardReader
        if (cardReader == null) {
            Log.e("nexgo", "Card reader is null")
            _transactionState.postValue(TransactionState.Error("Lector de tarjetas no disponible"))
            emvProcessListener?.onRequestShowToast("Error: Lector de tarjetas no disponible")
            return
        }

        // Configurar tipos de ranuras para búsqueda
        val slotTypes = HashSet<CardSlotTypeEnum>()
        slotTypes.add(CardSlotTypeEnum.ICC1)  // Tarjeta de contacto
        slotTypes.add(CardSlotTypeEnum.RF)    // Tarjeta sin contacto (NFC)

        // Configurar parámetros adicionales
        val searchTimeout = 60  // Tiempo de espera de búsqueda en segundos

        Log.d("nexgo", "Iniciando búsqueda de tarjetas...")
        Log.d("nexgo", "Ranuras a buscar: $slotTypes")
        Log.d("nexgo", "Tiempo de espera: $searchTimeout segundos")

        // Cambiar estado de la transacción
        _transactionState.postValue(TransactionState.CardReading)
        emvProcessListener?.onEmvProcessStarted()

        // Buscar tarjeta
        val searchResult = cardReader.searchCard(slotTypes, searchTimeout, this)

        // Registrar resultado de la búsqueda
        Log.d("nexgo", "Resultado de búsqueda de tarjeta: $searchResult")

        // Manejar posibles errores de búsqueda
        if (searchResult != SdkResult.Success) {
            val errorMessage = when (searchResult) {
                SdkResult.TimeOut -> "Tiempo de espera de búsqueda agotado"
                SdkResult.Device_Not_Ready -> "Dispositivo no está listo"
                SdkResult.Picc_Not_Open -> "Lector de tarjetas sin contacto no abierto"
                SdkResult.Picc_Not_Searched_Card -> "No se encontró tarjeta"
                SdkResult.Picc_Card_Too_Many -> "Se detectaron múltiples tarjetas"
                else -> "Error desconocido al buscar tarjeta: $searchResult"
            }

            Log.e("nexgo", errorMessage)
            _transactionState.postValue(TransactionState.Error(errorMessage))
            emvProcessListener?.onRequestShowToast(errorMessage)
        }
    }

    override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
        Log.d("nexgo", "onCardInfo - Código de retorno: $retCode")

        if (retCode == SdkResult.Success && cardInfo != null) {
            // Registrar información detallada de la tarjeta
            Log.d("nexgo", "Tarjeta detectada:")
            Log.d("nexgo", "Ranura de tarjeta: ${cardInfo.cardExistslot}")
            Log.d("nexgo", "Número de tarjeta: ${cardInfo.cardNo}")
            Log.d("nexgo", "Track 2: ${cardInfo.tk2}")

            mExistSlot = cardInfo.cardExistslot
            val transData = EmvTransConfigurationEntity()
            transData.transAmount = amount
            transData.emvTransType = 0x00.toByte() // 0x00-sale
            transData.countryCode = "840" // CountryCode
            transData.currencyCode = "840" // CurrencyCode, 840 indicate USD dollar
            transData.termId = "00000001"
            transData.merId = "000000000000001"
            transData.transDate = SimpleDateFormat("yyMMdd", Locale.getDefault()).format(Date())
            transData.transTime = SimpleDateFormat("hhmmss", Locale.getDefault()).format(Date())
            transData.traceNo = "00000000"

            transData.emvProcessFlowEnum = EmvProcessFlowEnum.EMV_PROCESS_FLOW_STANDARD
            if (cardInfo.cardExistslot == CardSlotTypeEnum.RF) {
                Log.d("nexgo", "Tarjeta sin contacto detectada")
                transData.emvEntryModeEnum = EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS
            } else {
                Log.d("nexgo", "Tarjeta de contacto detectada")
                transData.emvEntryModeEnum = EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACT
            }

            // Configuración para UPI
            val unionPayTransDataEntity = UnionPayTransDataEntity()
            unionPayTransDataEntity.setQpbocForGlobal(true)
            unionPayTransDataEntity.isSupportCDCVM = true
            transData.unionPayTransDataEntity = unionPayTransDataEntity

            // Iniciar proceso EMV
            _transactionState.postValue(TransactionState.ProcessingEmv)
            Log.d("nexgo", "Iniciando proceso EMV")
            emvHandler2?.emvProcess(transData, this)
        } else {
            // Registrar detalles del error
            val errorMessage = when (retCode) {
                SdkResult.Picc_Not_Searched_Card -> "No se encontró tarjeta sin contacto"
                SdkResult.Picc_Card_Too_Many -> "Se detectaron múltiples tarjetas"
                SdkResult.Picc_Card_No_Activation -> "Tarjeta sin contacto no activada"
                SdkResult.Icc_No_Reset_Card -> "No se pudo reiniciar la tarjeta ICC"
                else -> "Error al leer la tarjeta: $retCode"
            }

            Log.e("nexgo", errorMessage)
            _transactionState.postValue(TransactionState.Error(errorMessage))
            emvProcessListener?.onRequestShowToast(errorMessage)
        }
    }

    override fun onSwipeIncorrect() {
        _transactionState.postValue(TransactionState.Error("Deslizamiento incorrecto, intente nuevamente"))
        emvProcessListener?.onRequestShowToast("Deslizamiento incorrecto, intente nuevamente")
    }

    override fun onMultipleCards() {
        _transactionState.postValue(TransactionState.Error("Se detectaron múltiples tarjetas, use solo una"))
        emvProcessListener?.onRequestShowToast("Se detectaron múltiples tarjetas, use solo una")
    }

    override fun onSelApp(
        appNameList: List<String>, appInfoList: List<CandidateAppInfoEntity>, isFirstSelect: Boolean
    ) {
        Log.d("nexgo", "onSelApp")
        _transactionState.postValue(TransactionState.SelectApplication(appNameList))
        cardSelectionListener?.onCardSelectionRequired(appNameList, appInfoList)
    }

    override fun onTransInitBeforeGPO() {
        Log.d("nexgo", "onTransInitBeforeGPO")
        val aid = emvHandler2?.getTlv(byteArrayOf(0x4F), EmvDataSourceEnum.FROM_KERNEL)

        // Configuración específica según el tipo de tarjeta
        if (mExistSlot == CardSlotTypeEnum.RF) {
            if (aid != null) {
                when {
                    ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                        .contains("A000000004") -> {
                        // Paypass
                        configPaypassParameter(aid)
                    }

                    ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                        .contains("A000000003") -> {
                        // Paywave
                        // configPaywaveParameters()
                    }

                    ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                        .contains("A000000025") -> {
                        // ExpressPay
                        // configExpressPayParameter()
                    }
                }
            }
        }

        emvHandler2?.onSetTransInitBeforeGPOResponse(true)
    }

    override fun onContactlessTapCardAgain() {
        Log.d("nexgo", "onContactlessTapCardAgain")
        // Este método solo se usa para tarjetas EMV sin contacto si el host responde con script
    }

    override fun onConfirmCardNo(cardInfo: CardInfoEntity) {
        Log.d("nexgo", "onConfirmCardNo")
        Log.d("nexgo", "onConfirmCardNo" + cardInfo.tk2)
        Log.d("nexgo", "onConfirmCardNo" + cardInfo.cardNo)

        if (mExistSlot == CardSlotTypeEnum.RF) {
            emvHandler2?.onSetConfirmCardNoResponse(true)
            return
        }

        cardNo = cardInfo.cardNo
        _transactionState.postValue(TransactionState.ConfirmCardNumber(cardNo ?: ""))
        cardSelectionListener?.onCardNumberConfirmation(cardNo ?: "")
    }

    override fun onCardHolderInputPin(isOnlinePin: Boolean, leftTimes: Int) {
        Log.d("nexgo", "onCardHolderInputPin isOnlinePin = $isOnlinePin")
        Log.d("nexgo", "onCardHolderInputPin leftTimes = $leftTimes")

        _transactionState.postValue(TransactionState.PinRequested)
        showInputPin(isOnlinePin)
    }

    override fun onRemoveCard() {
        Log.d("nexgo", "onRemoveCard")
        emvHandler2?.onSetRemoveCardResponse()
    }

    override fun onPrompt(promptEnum: PromptEnum) {
        Log.d("nexgo", "onPrompt->$promptEnum")
        emvHandler2?.onSetPromptResponse(true)
    }

    override fun onOnlineProc() {
        Log.d("nexgo", "onOnlineProc")

        // Actualizar el estado para mostrar el diálogo de procesamiento
        _transactionState.postValue(TransactionState.ProcessingOnline)

        // Obtener datos importantes para la transacción
        Log.d("nexgo", "getEmvContactlessMode:" + emvHandler2?.emvContactlessMode)
        Log.d("nexgo", "getcardinfo:" + Gson().toJson(emvHandler2?.emvCardDataInfo))
        Log.d("nexgo", "getEmvCvmResult:" + emvHandler2?.emvCvmResult)
        Log.d("nexgo", "getSignNeed--" + emvHandler2?.signNeed)

        // Obtener TLVs importantes para enviar al servidor
        val tlv_5A = emvHandler2?.getTlv(byteArrayOf(0x5A.toByte()), EmvDataSourceEnum.FROM_KERNEL)
        Log.d("nexgo", "PAN (5A)--" + (tlv_5A?.let { ByteUtils.byteArray2HexString(it) } ?: "null"))

        val tlv_95 = emvHandler2?.getTlv(byteArrayOf(0x95.toByte()), EmvDataSourceEnum.FROM_KERNEL)
        Log.d("nexgo", "TVR (95)--" + (tlv_95?.let { ByteUtils.byteArray2HexString(it) } ?: "null"))

        val tlv_84 = emvHandler2?.getTlv(byteArrayOf(0x84.toByte()), EmvDataSourceEnum.FROM_KERNEL)
        Log.d("nexgo", "AID (84)--" + (tlv_84?.let { ByteUtils.byteArray2HexString(it) } ?: "null"))

        val tlv_50 = emvHandler2?.getTlv(byteArrayOf(0x50.toByte()), EmvDataSourceEnum.FROM_KERNEL)
        Log.d(
            "nexgo",
            "Application Label (50)--" + (tlv_50?.let { ByteUtils.byteArray2HexString(it) }
                ?: "null"))

        val tlv_9F26 = emvHandler2?.getTlv(
            byteArrayOf(0x9F.toByte(), 0x26.toByte()),
            EmvDataSourceEnum.FROM_KERNEL
        )
        Log.d(
            "nexgo",
            "Application Cryptogram (9F26)--" + (tlv_9F26?.let { ByteUtils.byteArray2HexString(it) }
                ?: "null"))

        val tlv_9F27 = emvHandler2?.getTlv(
            byteArrayOf(0x9F.toByte(), 0x27.toByte()),
            EmvDataSourceEnum.FROM_KERNEL
        )
        Log.d(
            "nexgo",
            "Cryptogram Information Data (9F27)--" + (tlv_9F27?.let {
                ByteUtils.byteArray2HexString(it)
            } ?: "null"))

        val tlv_9F36 = emvHandler2?.getTlv(
            byteArrayOf(0x9F.toByte(), 0x36.toByte()),
            EmvDataSourceEnum.FROM_KERNEL
        )
        Log.d(
            "nexgo",
            "Application Transaction Counter (9F36)--" + (tlv_9F36?.let {
                ByteUtils.byteArray2HexString(it)
            } ?: "null"))

        // TODO: Aquí debes implementar la llamada a tu API para procesar la compra
        // Ejemplo de cómo construir un mapa con los datos TLV para enviar al servidor
        val tlvMap = HashMap<String, String>()
        tlv_5A?.let { tlvMap["5A"] = ByteUtils.byteArray2HexString(it) }
        tlv_95?.let { tlvMap["95"] = ByteUtils.byteArray2HexString(it) }
        tlv_84?.let { tlvMap["84"] = ByteUtils.byteArray2HexString(it) }
        tlv_50?.let { tlvMap["50"] = ByteUtils.byteArray2HexString(it) }
        tlv_9F26?.let { tlvMap["9F26"] = ByteUtils.byteArray2HexString(it) }
        tlv_9F27?.let { tlvMap["9F27"] = ByteUtils.byteArray2HexString(it) }
        tlv_9F36?.let { tlvMap["9F36"] = ByteUtils.byteArray2HexString(it) }

        // También puedes obtener el campo 55 completo para enviar al servidor
        // Este campo contiene todos los TLVs necesarios para la autorización
        val field55 = buildField55()
        Log.d("nexgo", "Field 55: $field55")

        // Llamada a la API (simulada)
        processPaymentWithApi(tlvMap, field55)

        // Para este ejemplo, simulamos una respuesta exitosa del servidor
        val emvOnlineResult = EmvOnlineResultEntity()
        emvOnlineResult.authCode = "123450"  // Código de autorización del servidor
        emvOnlineResult.rejCode = "00"       // Código de respuesta (00 = aprobado)

        // Si el servidor devuelve datos EMV en formato TLV (campo 55 de respuesta)
        // debes asignarlos aquí para el segundo proceso de autenticación
        emvOnlineResult.recvField55 =
            null   // Ejemplo: ByteUtils.hexString2ByteArray("910870741219600860008a023030")

        // Informar al SDK el resultado de la transacción en línea
        emvHandler2?.onSetOnlineProcResponse(SdkResult.Success, emvOnlineResult)
    }

    /**
     * Construye el campo 55 (EMV Data) para enviar al servidor
     * Este campo contiene todos los TLVs necesarios para la autorización
     */
    private fun buildField55(): String {
        // Lista de tags EMV comúnmente requeridos para autorización
        val commonTags = arrayOf(
            byteArrayOf(0x5F, 0x2A.toByte()),        // 5F2A - Moneda
            byteArrayOf(0x82.toByte()),              // 82 - AIP
            byteArrayOf(0x84.toByte()),              // 84 - DF Name
            byteArrayOf(0x95.toByte()),              // 95 - TVR
            byteArrayOf(0x9A.toByte()),              // 9A - Fecha de transacción
            byteArrayOf(0x9C.toByte()),              // 9C - Tipo de transacción
            byteArrayOf(0x9F.toByte(), 0x02.toByte()),        // 9F02 - Monto
            byteArrayOf(0x9F.toByte(), 0x03.toByte()),        // 9F03 - Monto adicional
            byteArrayOf(
                0x9F.toByte(),
                0x10.toByte()
            ),        // 9F10 - Datos de aplicación específicos del emisor
            byteArrayOf(0x9F.toByte(), 0x1A.toByte()),        // 9F1A - Código de país del terminal
            byteArrayOf(0x9F.toByte(), 0x26.toByte()),        // 9F26 - Criptograma de aplicación
            byteArrayOf(
                0x9F.toByte(),
                0x27.toByte()
            ),        // 9F27 - Datos de información del criptograma
            byteArrayOf(
                0x9F.toByte(),
                0x36.toByte()
            ),        // 9F36 - Contador de transacciones de aplicación
            byteArrayOf(
                0x9F.toByte(),
                0x37.toByte()
            )         // 9F37 - Número aleatorio no predecible
        )

        val field55Builder = StringBuilder()

        for (tag in commonTags) {
            val value = emvHandler2?.getTlv(tag, EmvDataSourceEnum.FROM_KERNEL)
            if (value != null) {
                val tagStr = ByteUtils.byteArray2HexString(tag)
                val lenStr = String.format("%02X", value.size)
                val valueStr = ByteUtils.byteArray2HexString(value)
                field55Builder.append(tagStr).append(lenStr).append(valueStr)
            }
        }

        return field55Builder.toString()
    }

    /**
     * Procesa el pago con la API del servidor
     * @param tlvMap Mapa de TLVs para enviar al servidor
     * @param field55 Campo 55 completo en formato hexadecimal
     */
    private fun processPaymentWithApi(tlvMap: HashMap<String, String>, field55: String) {
        // TODO: Implementar la llamada real a tu API
        // Ejemplo de cómo podrías estructurar la llamada usando Retrofit o similar

        viewModelScope.launch {
            try {
                // Ejemplo de estructura de datos para enviar al servidor
                val requestData = HashMap<String, Any>()
                requestData["amount"] = amount ?: "0"
                requestData["cardNo"] = cardNo ?: ""
                requestData["tlvData"] = tlvMap
                requestData["field55"] = field55

                // Aquí iría la llamada real a tu API
                // val response = apiService.processPayment(requestData)

                Log.d("nexgo", "API call would be made with data: ${Gson().toJson(requestData)}")

                // Procesar la respuesta
                // if (response.isSuccessful) {
                //    // Manejar respuesta exitosa
                // } else {
                //    // Manejar error
                // }
            } catch (e: Exception) {
                Log.e("nexgo", "Error calling API", e)
                // Manejar excepción
            }
        }
    }

    override fun onFinish(retCode: Int, entity: EmvProcessResultEntity?) {
        Log.d("nexgo", "onFinish retCode: $retCode, entity: ${entity != null}")

        // Verificar si es necesario volver a presentar la tarjeta (ExpressPay)
        var flag = false
        val aid = emvHandler2?.getTlv(byteArrayOf(0x4F), EmvDataSourceEnum.FROM_KERNEL)
        if (aid != null) {
            if (mExistSlot == CardSlotTypeEnum.RF) {
                if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                        .contains("A000000025")
                ) {
                    if (retCode == SdkResult.Emv_Plz_See_Phone) {
                        isExpressPaySeePhoneTapCardAgain = true
                        flag = true
                    }
                }
            }
        }
        if (!flag) {
            isExpressPaySeePhoneTapCardAgain = false
        }

        // Registrar información de la transacción
        Log.d("nexgo", "emvHandler2.getSignNeed()--" + emvHandler2?.signNeed)
        Log.d("nexgo", "getcardinfo:" + Gson().toJson(emvHandler2?.emvCardDataInfo))
        Log.d("nexgo", "getEmvCvmResult:" + emvHandler2?.emvCvmResult)

        // Notificar a la UI
        _transactionState.postValue(TransactionState.TransactionCompleted(retCode))
        _transactionState.postValue(TransactionState.Completed)
        emvProcessListener?.onEmvProcessFinished(retCode)
    }

    override fun onInputResult(retCode: Int, data: ByteArray?) {
        Log.d("nexgo",
            "onInputResult->:" + (data?.let { ByteUtils.byteArray2HexStringWithSpace(it) }
                ?: "null"))

        pinInputListener?.onPinInputComplete()

        if (retCode == SdkResult.Success || retCode == SdkResult.PinPad_No_Pin_Input || retCode == SdkResult.PinPad_Input_Cancel) {
            if (data != null) {
                val temp = ByteArray(8)
                System.arraycopy(data, 0, temp, 0, 8)
            }
            emvHandler2?.onSetPinInputResponse(
                retCode != SdkResult.PinPad_Input_Cancel, retCode == SdkResult.PinPad_No_Pin_Input
            )
        } else {
            pinInputListener?.onPinInputFailed("Error al ingresar el PIN")
            emvHandler2?.onSetPinInputResponse(false, false)
        }
    }

    override fun onSendKey(keyCode: Byte) {
        when (keyCode) {
            PinPadKeyCode.KEYCODE_CLEAR -> {
                pwdText = ""
                _passwordPIN.postValue("")
            }

            PinPadKeyCode.KEYCODE_CANCEL -> {
                // No hacer nada, se manejará en onInputResult
            }

            else -> {
                pwdText = (pwdText ?: "") + "*"
                _passwordPIN.postValue(pwdText)
            }
        }

        // Notificar al listener
        pinInputListener?.onPinTextChanged(pwdText ?: "")
    }


    // Métodos auxiliares

    private fun showInputPin(isOnlinePin: Boolean) {
        pwdText = ""
        _passwordPIN.postValue("")

        val pinPad = deviceEngine?.pinPad
        pinPad?.setPinKeyboardMode(PinKeyboardModeEnum.FIXED)

        if (isOnlinePin) {
            if (cardNo == null) {
                cardNo = emvHandler2?.emvCardDataInfo?.cardNo
            }
            pinPad?.inputOnlinePin(
                intArrayOf(0x00, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c),
                60,
                cardNo!!.toByteArray(),
                10,
                PinAlgorithmModeEnum.ISO9564FMT1,
                this
            )
        } else {
            pinPad?.inputOfflinePin(
                intArrayOf(0x00, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c), 60, this
            )
        }
    }

    private fun configPaypassParameter(aid: ByteArray) {
        // Configuración del kernel, habilitar RRP y cdcvm
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x1B.toByte()), byteArrayOf(0x30.toByte())
        )

        // EMV MODE: monto > límite cvm sin contacto, establecer 60 = pin en línea y firma
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()), byteArrayOf(0x60.toByte())
        )

        // EMV mode: monto < límite cvm sin contacto, establecer 08 = sin cvm
        emvHandler2?.setTlv(
            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()), byteArrayOf(0x08.toByte())
        )

        // Configuración específica para Maestro
        if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
                .contains("A0000000043060")
        ) {
            Log.d("nexgo", "======maestro===== ")
            // Maestro solo soporta PIN en línea
            emvHandler2?.setTlv(
                byteArrayOf(0x9F.toByte(), 0x33.toByte()),
                byteArrayOf(0xE0.toByte(), 0x40.toByte(), 0xC8.toByte())
            )
            emvHandler2?.setTlv(
                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()), byteArrayOf(0x40.toByte())
            )
            emvHandler2?.setTlv(
                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()), byteArrayOf(0x08.toByte())
            )
        }
    }

    private fun leftPad(str: String?, size: Int, padChar: Char): String {
        val padded = StringBuilder(str ?: "")
        while (padded.length < size) {
            padded.insert(0, padChar)
        }
        return padded.toString()
    }
}
