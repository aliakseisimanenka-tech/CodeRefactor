package com.lsimanenka.domain

interface PaymentRepository {

    suspend fun getPaymentDetails(): Result<Payment>

}