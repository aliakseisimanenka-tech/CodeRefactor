package com.lsimanenka.data.datasource.local

import com.lsimanenka.database.PaymentDao
import com.lsimanenka.database.PaymentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentLocalDataSourceImpl @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentLocalDataSource {

    override fun getPaymentFlow(): Flow<PaymentEntity?> {
        return paymentDao.getPaymentFlow()
    }

    override suspend fun savePayment(payment: PaymentEntity) {
        paymentDao.savePayment(payment)
    }
}