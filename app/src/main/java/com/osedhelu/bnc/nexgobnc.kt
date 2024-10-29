package com.osedhelu.bnc

import android.app.Application
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class nexgobnc : Application() {
    var deviceEngine: DeviceEngine? = null

    override fun onCreate() {
        super.onCreate()
        try {
            deviceEngine = APIProxy.getDeviceEngine(this)
            deviceEngine?.getEmvHandler("app1")

        } catch (err: Exception) {
            println("xxoxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Exception $err")
        } catch (err: Throwable) {
            println("xxoxxxxxxxxxxxxxxxxxxxxxxxxxxxxx android Throwable")
        }

    }
}