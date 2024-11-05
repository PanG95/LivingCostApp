package com.example.livingcostapp.presentation.mainScreen.earnings

sealed class EarningsUiAction {
    data class AddIncome(val amount: Double) : EarningsUiAction()
}