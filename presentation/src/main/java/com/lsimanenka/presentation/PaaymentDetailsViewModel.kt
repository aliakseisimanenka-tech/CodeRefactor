package com.lsimanenka.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsimanenka.domain.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentDetailsViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaymentUiState>(PaymentUiState.Loading)

    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    init {
        loadPayment()
    }

    fun loadPayment() {
        viewModelScope.launch {
            _uiState.value = PaymentUiState.Loading

            repository.getPaymentDetails()
                .onSuccess { payment ->
                    _uiState.value = PaymentUiState.Content(payment)
                }
                .onFailure { error ->
                    _uiState.value = PaymentUiState.Error(
                        error.localizedMessage ?: "Неизвестная ошибка загрузки"
                    )
                }
        }
    }
}