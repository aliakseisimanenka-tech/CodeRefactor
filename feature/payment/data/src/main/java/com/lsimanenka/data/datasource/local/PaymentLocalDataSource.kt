package com.lsimanenka.data.datasource.local

import com.lsimanenka.database.PaymentEntity
import kotlinx.coroutines.flow.Flow

interface PaymentLocalDataSource {
    fun getPaymentFlow(): Flow<PaymentEntity?>
    suspend fun savePayment(payment: PaymentEntity)
}