package com.example.livingcostapp.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

class WelcomeScreen(navController: NavHostController) : ViewModel() {
    fun onLoginClicked() {
        viewModelScope.launch {
            println("Zaloguj kliknięty")
        }
    }

}