package com.disglobal.bnc.utils.di

import android.content.Context
import com.disglobal.bnc.nexgobnc
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmvModule {
    
    @Provides
    @Singleton
    fun provideDeviceEngine(@ApplicationContext context: Context): DeviceEngine {
        return (context.applicationContext as nexgobnc).deviceEngine!!
    }
    
    @Provides
    @Singleton
    fun provideCardReader(deviceEngine: DeviceEngine): CardReader {
        return deviceEngine.cardReader
    }
    
    @Provides
    @Singleton
    fun provideEmvHandler(deviceEngine: DeviceEngine): EmvHandler2 {
        return deviceEngine.getEmvHandler2("DISGLOBAL_EMV")
    }
}