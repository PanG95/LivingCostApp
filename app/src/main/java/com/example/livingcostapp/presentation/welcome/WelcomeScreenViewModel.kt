package com.example.livingcostapp.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor() : ViewModel() {

    fun onLoginClicked() {
        viewModelScope.launch {
            println("Zaloguj kliknięty")
        }
    }
}