package com.disglobal.bnc.utils


fun cumpleExpresion(cadena: String): Boolean {
    val aa = cadena.trimStart('0')
    val expresionRegular = "^(?!0)[0-9]{0,9}$".toRegex()
    return expresionRegular.matches(aa)
}


fun validarNumeroTelefono(cadena: String): Boolean {
    val aa = cadena.trimStart('0')
    val expresionRegular = "^(?!0)[0-9]{0,7}$".toRegex()
    return expresionRegular.matches(aa)
}

fun validarNumeroPrice(cadena: String): Boolean {
    val aa = cadena.trimStart('0')
    val expresionRegular = "^(?!0)[0-9]{0,7}$".toRegex()
    return expresionRegular.matches(aa)
}

fun validarNumCreditCard(cadena: String): Boolean {
    val expresionRegular = "^(?!0)[0-9]{0,16}$".toRegex()
    return expresionRegular.matches(cadena)
}

fun validarNumeroTarjetaCredito(cadena: String): Boolean {
    val expresionRegular = "^4[0-9]{12}(?:[0-9]{3})?$".toRegex()
    return expresionRegular.matches(cadena)
}