package com.disglobal.bnc.utils

import java.text.DecimalFormat


fun FormatePriceUtils(price: String): String {
    try {
        val forma = DecimalFormat("#,##0.00")
        return forma.format((price.toDouble() / 100))
    } catch (e: Exception) {
        return "0.00"
    }
}

fun FormatePriceUtilsDouble(price: Double): String {
    try {
        val forma = DecimalFormat("#,##0.00")
        return forma.format((price / 100))
    } catch (e: Exception) {
        return "0.00"
    }
}

fun FormatePriceUtilString(price: String): String {
    try {
        val forma = DecimalFormat("#,##0.00")
        val price1 = price.toDouble()
        return forma.format((price1 / 100))
    } catch (e: Exception) {
        return "0.00"
    }
}

fun convertirANumeroDecimal(numero: String): Double {
    try {
        val longitud = numero.length
        return if (longitud == 1) {
            "0.0$numero".toDouble()
        } else if (longitud == 2) {
            "0.$numero".toDouble()
        } else {
            (numero.substring(0, longitud - 2) + "." + numero.substring(longitud - 2)).toDouble()
        }
    } catch (e: Exception) {
        return 0.0
    }

}
