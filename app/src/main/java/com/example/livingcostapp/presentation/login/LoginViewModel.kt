package com.example.livingcostapp.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state

    fun handleAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.NavigateToMain -> {
                _state.update { it.copy(navigateToMain = true) }
            }
        }
    }

    data class LoginScreenState(
        val title: String = "Koszty Życia",
        val navigateToMain: Boolean = false
    )
}
