package com.example.livingcostapp.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state

    fun handleAction(action: MainUiAction) {
        when (action) {
            is MainUiAction.NavigateToEarnings -> {
                _state.update { it.copy(navigationTarget = MainNavigationTarget.Earnings) }
            }

            is MainUiAction.NavigateToExpenses -> {
                _state.update { it.copy(navigationTarget = MainNavigationTarget.Expenses) }
            }

            is MainUiAction.NavigateToSavings -> {
                _state.update { it.copy(navigationTarget = MainNavigationTarget.Savings) }
            }
        }
    }

    fun getTransactionsByType(type: TransactionType): LiveData<List<TransactionLiveCost>> {
        return repository.getTransactionsByType(type) ?: MutableLiveData(emptyList())
    }

    fun insert(transactionLiveCost: TransactionLiveCost) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(transactionLiveCost)
    }

    fun delete(transactionLiveCost: TransactionLiveCost) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(transactionLiveCost)
    }
}


