package com.lsimanenka.presentation

import com.lsimanenka.domain.Payment

sealed interface PaymentUiState {

    data object Loading : PaymentUiState

    data class Content(
        val payment: Payment
    ) : PaymentUiState

    data class Error(
        val message: String
    ) : PaymentUiState
}