package com.example.livingcostapp.presentation.mainScreen.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsViewModel

class ExpensesViewModelFactory(
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpensesViewModel::class.java)) {
            return ExpensesViewModel(transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
