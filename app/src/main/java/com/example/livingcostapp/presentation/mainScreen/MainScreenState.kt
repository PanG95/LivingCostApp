package com.example.livingcostapp.presentation.mainScreen

import com.example.livingcostapp.data.model.TransactionLiveCost

data class MainScreenState(
    val navigationTarget: MainNavigationTarget? = null,
    val transactions: List<TransactionLiveCost> = emptyList(),
)


