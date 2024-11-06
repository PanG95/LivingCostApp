package com.example.livingcostapp.presentation.mainScreen.savings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livingcostapp.domain.repository.TransactionRepository

class SavingsViewModelFactory(

    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavingsViewModel::class.java)) {
            return SavingsViewModel(transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
