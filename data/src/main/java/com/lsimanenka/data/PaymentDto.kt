package com.lsimanenka.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentDto(
    @SerialName("id") val id: String?,
    @SerialName("amount") val amount: Double?,
    @SerialName("currency") val currency: String?,
    @SerialName("recipientName") val recipientName: String?
)