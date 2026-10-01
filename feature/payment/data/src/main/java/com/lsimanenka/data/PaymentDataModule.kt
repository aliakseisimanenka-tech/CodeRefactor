package com.lsimanenka.data

import com.lsimanenka.data.datasource.local.PaymentLocalDataSource
import com.lsimanenka.data.datasource.local.PaymentLocalDataSourceImpl
import com.lsimanenka.data.datasource.remote.PaymentRemoteDataSource
import com.lsimanenka.data.datasource.remote.PaymentRemoteDataSourceImpl
import com.lsimanenka.database.AppDatabase
import com.lsimanenka.database.PaymentDao
import com.lsimanenka.domain.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentDataModule {

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        impl: PaymentRepositoryImpl
    ): PaymentRepository

    @Binds
    @Singleton
    abstract fun bindPaymentLocalDataSource(
        impl: PaymentLocalDataSourceImpl
    ): PaymentLocalDataSource

    @Binds
    @Singleton
    abstract fun bindPaymentRemoteDataSource(
        impl: PaymentRemoteDataSourceImpl
    ): PaymentRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun providePaymentApiService(retrofit: Retrofit): PaymentApiService {
            return retrofit.create(PaymentApiService::class.java)
        }

        @Provides
        @Singleton
        fun providePaymentDao(database: AppDatabase): PaymentDao {
            return database.paymentDao()
        }
    }
}