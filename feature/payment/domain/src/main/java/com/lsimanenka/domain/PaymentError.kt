package com.lsimanenka.domain

sealed interface PaymentError {
    data object PaymentNotFound : PaymentError
}