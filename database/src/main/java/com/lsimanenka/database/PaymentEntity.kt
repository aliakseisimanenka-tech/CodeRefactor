package com.lsimanenka.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentEntity(
    @PrimaryKey val id: String,
    val amount: Double,
    val currency: String,
    val recipientName: String
)