package com.lsimanenka.domain

data class Payment(
    val id: String,
    val amount: Double,
    val currency: String,
    val recipientName: String
)