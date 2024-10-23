package com.example.livingcostapp.presentation.mainScreen.earnings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EarningsScreenView() {
    EarningsScreenViewContent()
}

@Composable
fun EarningsScreenViewContent() {
    //   val expenses by viewModel(TransactionType.EXPENSE).observeAsState(emptyList())
    var amountInput by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Wydatki:")
        //    expenses.forEach { transaction ->
        //        Text(text = "Kwota: ${transaction.amount}, Data: ${transaction.date ?: "Brak daty"}")
    }
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = amountInput,
        onValueChange = { newValue -> amountInput = newValue },
        label = { Text(text = "Dodaj kwotę") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = {
        // Przykład obsługi zapisu nowej transakcji
        val amount = amountInput.text.toDoubleOrNull()
        if (amount != null) {
         //   state.insert(Transaction(amount = amount, type = TransactionType.EXPENSE))
            amountInput = TextFieldValue("")
            keyboardController?.hide() // Ukryj klawiaturę po dodaniu wartości
        }
    }) {
        Text(text = "Dodaj Wydatek")
    }
}
