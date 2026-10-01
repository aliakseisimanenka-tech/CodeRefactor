package com.lsimanenka.data.datasource.remote

import com.lsimanenka.data.PaymentDto

interface PaymentRemoteDataSource {
    suspend fun getPaymentDetails(): PaymentDto
}