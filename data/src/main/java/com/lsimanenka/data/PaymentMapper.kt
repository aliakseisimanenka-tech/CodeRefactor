package com.lsimanenka.data

import com.lsimanenka.database.PaymentEntity
import com.lsimanenka.domain.Payment

fun PaymentDto.toDomain(): Payment {
    return Payment(
        id = this.id.orEmpty(),
        amount = this.amount ?: 0.0,
        currency = this.currency.orEmpty(),
        recipientName = this.recipientName.orEmpty()
    )
}

fun PaymentEntity.toDomain(): Payment {
    return Payment(
        id = this.id,
        amount = this.amount,
        currency = this.currency,
        recipientName = this.recipientName
    )
}

fun Payment.toEntity(): PaymentEntity {
    return PaymentEntity(
        id = this.id,
        amount = this.amount,
        currency = this.currency,
        recipientName = this.recipientName
    )
}