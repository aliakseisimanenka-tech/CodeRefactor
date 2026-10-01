package com.lsimanenka.data

import com.lsimanenka.data.datasource.local.PaymentLocalDataSource
import com.lsimanenka.data.datasource.remote.PaymentRemoteDataSource
import com.lsimanenka.domain.AppResult
import com.lsimanenka.domain.Payment
import com.lsimanenka.domain.PaymentError
import com.lsimanenka.domain.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val remoteDataSource: PaymentRemoteDataSource,
    private val localDataSource: PaymentLocalDataSource
) : PaymentRepository {

    override fun getPaymentFlow(): Flow<Payment?> {
        return localDataSource.getPaymentFlow().map { it?.toDomain() }
    }

    override suspend fun refreshPayment(): AppResult<Unit, PaymentError> {
        return try {
            val dto = remoteDataSource.getPaymentDetails()
            val payment = dto.toDomain()
            localDataSource.savePayment(payment.toEntity())
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Error(PaymentError.PaymentNotFound)
        }
    }
}
