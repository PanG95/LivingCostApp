package com.example.livingcostapp.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun string(id: Int): String {
    return stringResource(id)
}