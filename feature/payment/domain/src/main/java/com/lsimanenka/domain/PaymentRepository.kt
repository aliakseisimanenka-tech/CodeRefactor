package com.lsimanenka.domain

import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    fun getPaymentFlow(): Flow<Payment?>

    suspend fun refreshPayment(): AppResult<Unit, PaymentError>

}