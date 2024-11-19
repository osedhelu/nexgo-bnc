package com.disglobal.bnc.data.local.di

import android.content.Context
import androidx.room.Room
import com.disglobal.bnc.data.local.database.AppDatabase
import com.disglobal.bnc.data.local.database.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

}
