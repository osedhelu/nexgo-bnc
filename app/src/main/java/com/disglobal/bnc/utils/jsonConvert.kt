package com.disglobal.bnc.utils


import com.google.gson.Gson

fun <T> jsonStringify(data: T): String {
    val gson = Gson()
    return gson.toJson(data)
}

inline fun <reified T> jsonParse(data: String): T {
    val gson = Gson()
    return gson.fromJson<T>("""$data""", T::class.java)

}