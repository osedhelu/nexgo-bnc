package com.disglobal.bnc.utils

fun redidText(e: String, init: Int = 3, fin: Int = 2): String {
    try {
        return "${e.substring(0, init)}***${e.substring(e.length - fin)}"
    } catch (err: Exception) {
        return e
    }
}