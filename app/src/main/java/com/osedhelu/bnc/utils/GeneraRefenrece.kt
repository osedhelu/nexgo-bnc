package com.osedhelu.bnc.utils


fun generarReferencia(longitud: Int = 12): String {
    val caracteres = "0123456789"
    val referencia = StringBuilder()

    while (referencia.length < longitud) {
        val indice = (caracteres.indices).random()
        val caracter = caracteres[indice]
        referencia.append(caracter)
    }

    return referencia.toString()
}
