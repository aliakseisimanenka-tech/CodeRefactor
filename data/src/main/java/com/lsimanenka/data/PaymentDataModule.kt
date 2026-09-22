package com.lsimanenka.data

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