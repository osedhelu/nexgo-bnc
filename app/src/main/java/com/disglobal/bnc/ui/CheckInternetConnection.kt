package com.disglobal.bnc.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.platform.LocalContext
import com.disglobal.bnc.ui.components.StatusCustomScreen

fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

@Composable
fun IsConnectedScreen(children: @Composable () -> Unit) {
    val ctx = LocalContext.current
    var isConnected by remember { mutableStateOf(true) }
    val connectivityManager =
        ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: android.net.Network) {
            isConnected = true
        }

        override fun onLost(network: android.net.Network) {
            isConnected = false
        }
    }

    DisposableEffect(Unit) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request =
                NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }

        // Initial check
        isConnected = checkInternetConnection(ctx)

        // Cleanup when the composable leaves the composition
        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
    if (isConnected) {
        children()
    } else {
        StatusCustomScreen(
            ok = false,
            message = "No Internet Connection",
            sudMessage = "Please check your network settings."
        )
    }
}

