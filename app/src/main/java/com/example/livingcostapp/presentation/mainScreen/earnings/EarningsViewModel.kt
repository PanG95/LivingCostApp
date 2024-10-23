package com.example.livingcostapp.presentation.mainScreen.earnings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class EarningsViewModel   {
//    : ViewModel()
//    private val _state = MutableStateFlow(EarningsScreenState())
//    val state: StateFlow<EarningsScreenState> = _state


    data class EarningsScreenState(
        val title: String = "Koszty Życia",
        val navigateToMain: Boolean = false
    )
}