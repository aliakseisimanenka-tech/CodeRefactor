package com.lsimanenka.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payments LIMIT 1")
    suspend fun getLastPayment(): PaymentEntity?

    @Upsert
    suspend fun savePayment(payment: PaymentEntity)
}