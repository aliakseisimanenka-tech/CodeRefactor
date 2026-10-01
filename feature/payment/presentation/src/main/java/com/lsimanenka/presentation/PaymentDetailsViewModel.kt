package com.lsimanenka.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsimanenka.domain.AppResult
import com.lsimanenka.domain.PaymentError
import com.lsimanenka.domain.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentDetailsViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

    private val _errorState = MutableStateFlow<UiText?>(null)

    val uiState : StateFlow<PaymentUiState> = combine(
        _errorState,
        repository.getPaymentFlow()
    ) {networkError, payment ->
        when {
            payment != null -> PaymentUiState.Content(payment)
            networkError != null -> PaymentUiState.Error(networkError)
            else -> PaymentUiState.Loading
        }


    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PaymentUiState.Loading)

    init {
        refreshPayment()
    }

    fun refreshPayment() {
        viewModelScope.launch {

            _errorState.value = null

            val result = repository.refreshPayment()
            when (result) {
                is AppResult.Success -> {}
                is AppResult.Error -> {
                    val message = when (result.error) {
                        PaymentError.PaymentNotFound -> UiText.StringResource(R.string.payment_not_found)
                    }
                    _errorState.value = message
                }
            }
        }
    }
}