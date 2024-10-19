package com.example.livingcostapp.presentation.mainScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
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
}
