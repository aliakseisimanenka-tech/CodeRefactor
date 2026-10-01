package com.lsimanenka.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payments LIMIT 1")
    fun getPaymentFlow(): Flow<PaymentEntity?>

    @Upsert
    suspend fun savePayment(payment: PaymentEntity)
}