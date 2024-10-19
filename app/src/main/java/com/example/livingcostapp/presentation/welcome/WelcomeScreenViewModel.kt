package com.example.livingcostapp.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(WelcomeScreenState())
    val state: StateFlow<WelcomeScreenState> = _state

    fun handleAction(action: WelcomeUiAction) {
        when (action) {
            is WelcomeUiAction.LoginClick -> {
                // Handle login click
            }
        }
    }

    data class WelcomeScreenState(
        val title: String = "Koszty Życia",
        val navigateToLogin: Boolean = false
    )
}
