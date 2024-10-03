package com.example.livingcostapp.presentation.welcome

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navController: NavController) {
    WelcomeScreenView(
        onLoginClick = {
            navController.navigate("login")
        }
    )
}