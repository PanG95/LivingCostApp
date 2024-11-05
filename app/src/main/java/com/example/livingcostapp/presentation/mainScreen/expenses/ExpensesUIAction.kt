package com.example.livingcostapp.presentation.mainScreen.expenses


sealed class ExpensesUIAction {
    data class AddExpenses(val amount: Double) : ExpensesUIAction()
}