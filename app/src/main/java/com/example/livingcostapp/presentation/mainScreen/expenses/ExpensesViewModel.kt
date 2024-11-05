package com.example.livingcostapp.presentation.mainScreen.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsUiAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _state = MutableStateFlow(EarningsScreenState())
    val state: StateFlow<EarningsScreenState> = _state
    private val _totalExpenses = MutableStateFlow(0.0)
    val totalExpenses: StateFlow<Double> = _totalExpenses
    fun getValue() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.EXPENSE).asFlow()
                .map { transactionList ->
                    transactionList.sumOf { it.amount }
                }
                .collect { total ->
                    _totalExpenses.value = total
                }
        }
    }

    fun addExpenses(amount: Double) {
        viewModelScope.launch {
            val transaction = TransactionLiveCost(amount = amount, type = TransactionType.EXPENSE)
            repository.insert(transaction)
        }
        calculateTotalExpenses()
    }

    data class EarningsScreenState(
        val title: String = "Koszty Życia",
        val navigateToMain: Boolean = false
    )

    fun handleAction(action: ExpensesUIAction) {
        when (action) {
            is ExpensesUIAction.AddExpenses -> addExpenses(action.amount)
        }
    }

    private fun calculateTotalExpenses() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.EXPENSE)
                .asFlow()
                .collect { transactions ->
                    val total = transactions.sumOf { it.amount }
                    _totalExpenses.value = total
                }
        }
    }
}
