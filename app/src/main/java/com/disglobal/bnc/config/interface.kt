package com.disglobal.bnc.config


data class Propsdropdown(
    val key: String = "",
    val title: String = ""
)

data class iValueCardTransacion(
    val telefono: String,
    val banco: String,
    val referencia: String,
    val monto: Double = 0.00,
    val state: Boolean = false,
    val type: String = "Receptor"
)