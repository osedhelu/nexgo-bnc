package com.disglobal.bnc.data.di

import com.disglobal.bnc.data.remote.apiBanco.ApiBancoRepository
import com.disglobal.bnc.data.remote.apiBanco.ApiBancoRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun BancoRepository(repo: ApiBancoRepositoryImp): ApiBancoRepository

}

