package com.disglobal.bnc.data.di

import android.app.Application
import com.disglobal.bnc.DigipayApi.domain.repositories.DigipayRepository
import com.disglobal.bnc.DigipayApi.infrastructure.repositories.DigipayRepositoryImp
import com.disglobal.bnc.features.common.emv.EmvRepository
import com.disglobal.bnc.nexgobnc
import com.disglobal.bnc.ui.test.EmvUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun BancoRepository(repo: DigipayRepositoryImp): DigipayRepository


}

