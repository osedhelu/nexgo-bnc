package com.disglobal.bnc.di

import android.app.Application
import com.disglobal.bnc.features.common.emv.EmvRepository
import com.disglobal.bnc.nexgobnc
import com.disglobal.bnc.ui.test.EmvUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmvModule {

    @Provides
    @Singleton
    fun provideDeviceEngine(application: Application): DeviceEngine {
        return (application as nexgobnc).deviceEngine 
            ?: throw IllegalStateException("DeviceEngine is not initialized")
    }

    @Provides
    @Singleton
    fun provideEmvUtils(application: Application): EmvUtils {
        return EmvUtils.build(application)
    }

    @Provides
    @Singleton
    fun provideEmvRepository(deviceEngine: DeviceEngine, emvUtils: EmvUtils): EmvRepository {
        return EmvRepository(deviceEngine, emvUtils)
    }
}