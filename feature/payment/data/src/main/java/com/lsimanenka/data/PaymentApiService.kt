package com.lsimanenka.data

import retrofit2.http.GET

interface PaymentApiService {

    @GET("payments/details")
    suspend fun getPaymentDetails(): PaymentDto
}