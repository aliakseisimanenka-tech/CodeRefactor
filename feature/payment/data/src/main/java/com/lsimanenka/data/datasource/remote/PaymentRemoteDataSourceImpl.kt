package com.lsimanenka.data.datasource.remote

import com.lsimanenka.data.PaymentApiService
import com.lsimanenka.data.PaymentDto
import javax.inject.Inject

class PaymentRemoteDataSourceImpl @Inject constructor(
    private val apiService: PaymentApiService
) : PaymentRemoteDataSource {

    override suspend fun getPaymentDetails(): PaymentDto {
        return apiService.getPaymentDetails()
    }
}