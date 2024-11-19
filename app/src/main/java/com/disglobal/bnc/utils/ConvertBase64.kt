package com.disglobal.bnc.utils


import android.os.Build
import java.util.*

fun ConvertToBase64(payload: String): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        return Base64.getEncoder().encodeToString(payload.toByteArray());
    } else {
        return android.util.Base64.encodeToString(
            payload.toByteArray(),
            android.util.Base64.NO_WRAP
        );
    }
}

fun getNumber(str: String): Int {
    return try {
        Integer.parseInt(str)
    } catch (e: NumberFormatException) {

        0
    }
}
