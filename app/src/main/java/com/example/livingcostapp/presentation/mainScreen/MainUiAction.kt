package com.example.livingcostapp.presentation.mainScreen

import com.example.livingcostapp.presentation.login.LoginUiAction

sealed class MainUiAction {
    object NavigateToExpenses : MainUiAction()
    object NavigateToSavings : MainUiAction()
    object NavigateToEarnings : MainUiAction()
}
