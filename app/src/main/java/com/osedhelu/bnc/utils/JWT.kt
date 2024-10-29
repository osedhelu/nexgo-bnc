package com.osedhelu.bnc.utils


import android.util.Base64
import com.osedhelu.bnc.config.HASH_ENCRIPT
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import com.osedhelu.bnc.config.JWT_SECRET
import org.json.JSONObject
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec


fun encriptar(datos: String): String {
    val password = JWT_SECRET
    val keySpec = SecretKeySpec(password.toByteArray(), "AES")
    val ivSpec = IvParameterSpec(password.toByteArray())
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
    val results = cipher.doFinal(datos.toByteArray())
    return Base64.encodeToString(results, Base64.NO_WRAP or Base64.DEFAULT)
}


fun desencriptar(datos: String): String {
    val password = JWT_SECRET
    val keySpec = SecretKeySpec(password.toByteArray(), "AES")
    val ivSpec = IvParameterSpec(password.toByteArray())
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
    return String(cipher.doFinal(Base64.decode(datos, Base64.DEFAULT)))
}

fun generateHmac(data: String, secret: String): String {
    val secretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
    val mac = Mac.getInstance("HmacSHA256")
    mac.init(secretKeySpec)
    val hmac = mac.doFinal(data.toByteArray())
    return Base64.encodeToString(hmac, Base64.DEFAULT)
}


fun encodeJSONHmac(sourceData: Map<String, Any?>, time: Long): String {
    // Crear un objeto JSON que contiene los datos fuente y propiedades adicionales
    val json = JSONObject(
        sourceData + mapOf(
            "exp" to (System.currentTimeMillis() + time),
            "iat" to System.currentTimeMillis()
        )
    ).toString()

    // Codificar los datos JSON en base64
    val encodedData = Base64.encodeToString(json.toByteArray(), Base64.DEFAULT)

    // Concatenar el resultado de la codificación base64 con el resultado del HMAC
    return "${encodedData}!${generateHmac(encodedData, HASH_ENCRIPT)}"
}