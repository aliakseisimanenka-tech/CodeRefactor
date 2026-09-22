package com.lsimanenka.data

import com.lsimanenka.database.PaymentDao
import com.lsimanenka.domain.Payment
import com.lsimanenka.domain.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val apiService: PaymentApiService,
    private val paymentDao: PaymentDao
) : PaymentRepository {

    override suspend fun getPaymentDetails(): Result<Payment> {
        return try {

            val dto = apiService.getPaymentDetails()
            val payment = dto.toDomain()
            paymentDao.savePayment(payment.toEntity())
            Result.success(payment)
        } catch (e: Exception) {
            val cachedPayment = paymentDao.getLastPayment()

            if (cachedPayment != null) {
                Result.success(cachedPayment.toDomain())
            } else {
                Result.failure(Exception("Не удалось загрузить данные и нет сохраненного кэша", e))
            }
        }
    }
}