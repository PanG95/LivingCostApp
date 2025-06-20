package com.example.livingcostapp.presentation.mainScreen.savings

sealed class SavingsUIAction {
    data class AddSavings(val amount: Double) : SavingsUIAction()
}