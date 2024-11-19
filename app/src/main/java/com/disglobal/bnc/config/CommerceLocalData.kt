package com.disglobal.bnc.config

import android.content.Context

data class CommerceLocalData(
    var id: Int,
    var commerceCode: String,
    var nameCommerce: String,
    var rif: String,
    var terminalCode: String,
    val token: String? = null
) {

    companion object {
        private const val PRESF_SESSION = "presfSession"
        private const val ID = "id_key"
        private const val COMMERCECODE = "commerceCode"
        private const val RIF = "rif"
        private const val NAME_COMMERCE = "NAME_COMMERCE"
        private const val TERMINALCODE = "terminalCode"
        private const val TOKEN = "token"

        // Guarda los datos del comercio en las preferencias compartidas
        fun setCommerce(context: Context, commerce: CommerceLocalData) {
            context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE).also {
                it.edit().putInt(ID, commerce.id)
                    .putString(COMMERCECODE, commerce.commerceCode)
                    .putString(NAME_COMMERCE, commerce.nameCommerce)
                    .putString(TERMINALCODE, commerce.terminalCode)
                    .putString(RIF, commerce.rif)
                    .putString(TOKEN, commerce.token)
                    .apply()
            }

        }

        // Recupera los datos del comercio de las preferencias compartidas
        fun getCommerce(context: Context): CommerceLocalData? {
            try {
                val prefs =
                    context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE) ?: return null
                val id_commerce = prefs.getInt(ID, 0)
                if (id_commerce === 0) {
                    return null
                }
                return CommerceLocalData(
                    id = prefs.getInt(ID, 0) ?: 0,
                    commerceCode = prefs.getString(COMMERCECODE, "") ?: "",
                    terminalCode = prefs.getString(TERMINALCODE, "") ?: "",
                    rif = prefs.getString(RIF, "") ?: "",
                    nameCommerce = prefs.getString(NAME_COMMERCE, "") ?: "",
                    token = prefs.getString(TOKEN, null)
                )
            } catch (e: Exception) {
                println("xxxxxxxxxxrrrrrrrrrrrrrrrrrrrrr$e")
                return null
            }
        }

        fun setTokenAuth(context: Context, token: String?) {
            context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE).also {
                it.edit().putString(TOKEN, token).apply()
            }
        }

        fun getTokenAuth(context: Context): String? {
            try {
                val prefs =
                    context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE) ?: return null
                return prefs.getString(TOKEN, null)
            } catch (e: Exception) {
                return null

            }

        }
    }
}
