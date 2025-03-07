package com.disglobal.bnc.data.di

import android.content.Context
import androidx.room.Room
import com.disglobal.bnc.DigipayApi.domain.datasources.DigipayDatasource
import com.google.gson.GsonBuilder
import com.disglobal.bnc.config.BASEURL
import com.disglobal.bnc.data.local.database.AppDatabase
import com.disglobal.bnc.data.local.database.TransactionDao
import com.disglobal.bnc.data.remote.createCustomOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    @Named("BACKEND_URL")
    fun providerUrl() = BASEURL

    @Singleton
    @Provides
    fun providerRetrofit(
        @ApplicationContext context: Context,
        @Named("BACKEND_URL") baseUrl: String,
    ): DigipayDatasource {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .client(createCustomOkHttpClient(context)).baseUrl(baseUrl).build()
            .create(DigipayDatasource::class.java)

    }

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db_bfc")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun TransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()

}
