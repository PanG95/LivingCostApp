package com.example.livingcostapp.presentation.mainScreen.savings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SavingsViewModel(
    private val repository: TransactionRepository) : ViewModel() {

    private val _state = MutableStateFlow(SavingsScreenState())
    val state: StateFlow<SavingsScreenState> = _state
    private val _totalSavings = MutableStateFlow(0.0)
    val totalSavings: StateFlow<Double> = _totalSavings
    fun getValue() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.SAVINGS).asFlow()
                .map { transactionList ->
                    transactionList.sumOf { it.amount }
                }
                .collect { total ->
                    _totalSavings.value = total
                }
        }
    }

    fun addSavings(amount: Double) {
        viewModelScope.launch {
            val transaction = TransactionLiveCost(amount = amount, type = TransactionType.SAVINGS)
            repository.insert(transaction)
        }
        calculateTotalSavings()
    }

    data class SavingsScreenState(
        val title: String = "Koszty Życia",
        val navigateToMain: Boolean = false
    )

    fun handleAction(action: SavingsUIAction) {
        when (action) {
            is SavingsUIAction.AddSavings -> addSavings(action.amount)
        }
    }

    private fun calculateTotalSavings() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.SAVINGS)
                .asFlow()
                .collect { transactions ->
                    val total = transactions.sumOf { it.amount }
                    _totalSavings.value = total
                }
        }
    }
}