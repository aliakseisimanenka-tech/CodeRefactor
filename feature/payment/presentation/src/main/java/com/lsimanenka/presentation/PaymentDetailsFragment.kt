package com.lsimanenka.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lsimanenka.domain.Payment
import com.lsimanenka.presentation.databinding.FragmentPaymentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentDetailsFragment : Fragment(R.layout.fragment_payment_details) {

    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentDetailsBinding.bind(view)

        binding.retryButton.setOnClickListener {
            viewModel.refreshPayment()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: PaymentUiState) {
        binding.progressBar.isVisible = state is PaymentUiState.Loading
        binding.contentGroup.isVisible = state is PaymentUiState.Content
        binding.errorGroup.isVisible = state is PaymentUiState.Error

        when (state) {
            is PaymentUiState.Loading -> {
            }
            is PaymentUiState.Content -> {
                bindPayment(state.payment)
            }
            is PaymentUiState.Error -> {
                binding.errorTextView.text = state.message.asString(context = requireContext())
            }
        }
    }

    private fun bindPayment(payment: Payment) {
        binding.amountTextView.text = payment.amount.toString()
        binding.currencyTextView.text = payment.currency
        binding.recipientTextView.text = payment.recipientName
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}