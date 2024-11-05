package com.example.livingcostapp.presentation.mainScreen.earnings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.domain.repository.TransactionRepositoryInterface

class EarningsViewModelFactory(
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EarningsViewModel::class.java)) {
            return EarningsViewModel(transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
