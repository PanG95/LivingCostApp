package com.example.livingcostapp.presentation.mainScreen.earnings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.domain.repository.TransactionRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class EarningsViewModel(private val repository: TransactionRepository)
 : ViewModel() {

    private val _state = MutableStateFlow(EarningsScreenState())
    val state: StateFlow<EarningsScreenState> = _state
    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome
    fun getValue() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.INCOME).asFlow()
                .map { transactionList ->
                    transactionList.sumOf { it.amount } // Obliczanie sumy wartości transakcji
                }
                .collect { total ->
                    _totalIncome.value = total // Zaktualizowanie wartości totalIncome
                }
        }
    }
    fun addIncome(amount: Double) {
        viewModelScope.launch {
            val transaction = TransactionLiveCost(amount = amount, type = TransactionType.INCOME)
            repository.insert(transaction)
        }
        calculateTotalIncome()
    }
    data class EarningsScreenState(
        val title: String = "Koszty Życia",
        val navigateToMain: Boolean = false
    )
    fun handleAction(action: EarningsUiAction) {
        when (action) {
            is EarningsUiAction.AddIncome -> addIncome(action.amount)
        }
    }
    private fun calculateTotalIncome() {
        viewModelScope.launch {
            repository.getTransactionsByType(TransactionType.INCOME)
                .asFlow()
                .collect { transactions ->
                    val total = transactions.sumOf { it.amount }
                    _totalIncome.value = total
                }
        }
    }
}