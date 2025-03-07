package com.nexgo.oaf.apiv3

object SdkResultMessages {
    fun getErrorMessage(resultCode: Int): String {
        return when (resultCode) {
            // Códigos generales
            SdkResult.Success -> "Operación exitosa"
            SdkResult.Fail -> "Operación fallida"
            SdkResult.Param_In_Invalid -> "Parámetro inválido"
            SdkResult.TimeOut -> "Tiempo de espera agotado"
            SdkResult.Device_Not_Ready -> "Dispositivo no está listo"
            SdkResult.NotSupport -> "Operación no soportada"
            SdkResult.Cancel -> "Operación cancelada"

            // Errores de impresora
            SdkResult.Printer_Print_Fail -> "Error al imprimir"
            SdkResult.Printer_AddPrnStr_Fail -> "Error al agregar texto para imprimir"
            SdkResult.Printer_Busy -> "Impresora ocupada"
            SdkResult.Printer_PaperLack -> "Sin papel en la impresora"
            SdkResult.Printer_Fault -> "Falla en la impresora"
            SdkResult.Printer_TooHot -> "Impresora demasiado caliente"

            // Errores de EMV
            SdkResult.Emv_Other_Interface -> "Usar otra interfaz"
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

            // Errores de tarjeta ICC
            SdkResult.Icc_PullOut_Card -> "Tarjeta ICC retirada"
            SdkResult.Icc_Parity_Err -> "Error de paridad en tarjeta ICC"
            SdkResult.Icc_Channel_Err -> "Error de canal en tarjeta ICC"

            // Errores de tarjeta PICC
            SdkResult.Picc_Not_Open -> "Lector de tarjeta PICC no abierto"
            SdkResult.Picc_Not_Searched_Card -> "No se encontró tarjeta PICC"
            SdkResult.Picc_Card_Too_Many -> "Demasiadas tarjetas PICC detectadas"

            // Errores de PIN
            SdkResult.PinPad_No_Pin_Input -> "No se ingresó PIN"
            SdkResult.PinPad_Input_Cancel -> "Ingreso de PIN cancelado"
            SdkResult.PinPad_Input_Timeout -> "Tiempo de espera de ingreso de PIN agotado"

            // Otros errores
            else -> "Código de error desconocido: $resultCode"
        }
    }
}

/**
 * Extensión para obtener un mensaje descriptivo de error
 */
fun Int.toErrorMessage(): String {
    return SdkResultMessages.getErrorMessage(this)
}