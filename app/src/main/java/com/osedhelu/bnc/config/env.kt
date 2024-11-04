package com.osedhelu.bnc.config

import com.osedhelu.bnc.BuildConfig

const val PRICE_VALIDATE = 1000

//Backend BANCK
const val SERIAL_DEFAULT = "1234567"
const val BASEURL = BuildConfig.baseUrl
const val APPNAME = BuildConfig.appName
const val APPVERSION = BuildConfig.appVersion
const val KSN = BuildConfig.ksn
const val AFFILIATION_ID = BuildConfig.affiliationId
const val USER_ID = BuildConfig.userId
const val MERCHANT = BuildConfig.merchant
const val TERMINAL = BuildConfig.terminal
const val LOT_NUMBER = BuildConfig.lotNumber

// endpoints api
const val GET_INFO_AFFILIATION = "/api-echo/v1/resumen"
const val BATCH_SUMMARY = "/api-echo/v1/transactions"
const val ECHO_TEST = "/api-echo/v1/users/gestion"
const val PATH_GET_TRANSACTION = "/api-echo/v1/transactions"
const val PATH_TRANSACTION_AFFILIATION = "/api-echo/v1/transactions/affiliation"
const val PATH_RECEIVER_STATUS = "/api-echo/v1/receiver/status"


//Api
const val JWT_SECRET = "1234567890123456"
const val HASH_ENCRIPT = "1234567890123456"


const val FontSize = 18


enum class TYPE_TRAP(val value: String) {
    VUELTO("vuelto"), COMP("comprobacion")
}


val ListTypeDocument: List<Propsdropdown> = listOf(
    Propsdropdown("V", "V"),
    Propsdropdown("P", "P"),
    Propsdropdown("E", "E"),
    Propsdropdown("J", "J"),
    Propsdropdown("G", "G"),
)

val ListTypeTransaction: List<Propsdropdown> = listOf(
    Propsdropdown("01", "Pago Móvil"),
    Propsdropdown("02", "Transferencia"),
)
val ListFormatFecha: List<Propsdropdown> = listOf(
    Propsdropdown("DD/MM/AA", "DD/MM/AA"),
)
val BancosList: List<Propsdropdown> = listOf(
    Propsdropdown("0102", "Banco de Venezuela S.A."),
    Propsdropdown("0104", "Venezolano de Crédito S.A."),
    Propsdropdown("0105", "Banco Mercantil, C.A "),
    Propsdropdown("0108", "Banco Provincial, S.A."),
    Propsdropdown("0114", "BANCO DEL CARIBE"),
    Propsdropdown("0115", "Banco Exterior C.A."),
    Propsdropdown("0128", "Banco Caroní C.A."),
    Propsdropdown("0134", "Banesco S.A.C.A."),
    Propsdropdown("0137", "Banco Sofitasa C.A."),
    Propsdropdown("0138", "Banco Plaza C.A."),
    Propsdropdown("0151", "BFC Banco Fondo Común C.A."),
    Propsdropdown("0156", "100% Banco, C.A."),
    Propsdropdown("0157", "DelSur, C.A."),
    Propsdropdown("0163", "Banco del Tesoro, C.A."),
    Propsdropdown("0166", "Banco Agrícola de Venezuela, C.A."),
    Propsdropdown("0168", "Bancrecer, S.A."),
    Propsdropdown("0169", "Mi Banco C.A."),
    Propsdropdown("0171", "Banco Activo, C.A."),
    Propsdropdown("0172", "Bancamiga, C.A."),
    Propsdropdown("0174", "Banplus, C.A."),
    Propsdropdown("0175", "Banco Bicentenario C.A."),
    Propsdropdown("0177", "BANFANB"),
    Propsdropdown("0191", "Banco Nacional de Crédito, C.A."),
)
val tipoCell = listOf<Propsdropdown>(
    Propsdropdown("412", "0412"),
    Propsdropdown("416", "0416"),
    Propsdropdown("414", "0414"),
    Propsdropdown("424", "0424"),
    Propsdropdown("426", "0426"),
)