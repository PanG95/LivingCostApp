package com.example.livingcostapp.presentation.mainScreen.earnings

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.livingcostapp.data.model.TransactionDao
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionType
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.mock.MockTransactionRepository
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EarningsScreenView(
    state: EarningsViewModel.EarningsScreenState,
    navController: NavHostController,
    onAddIncome: (Double) -> Unit,
    totalIncome: StateFlow<Double>,
) {
    EarningsScreenViewContent(state, navController,totalIncome, onAddIncome)
}

@Composable
fun EarningsScreenViewContent(
    state: EarningsViewModel.EarningsScreenState,
    navController: NavHostController,
    totalIncome: StateFlow<Double>,
    onAddIncome: (Double) -> Unit
) {
    var amountInput by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val mockRepository = MockTransactionRepository()
    val income by totalIncome.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Wydatki:")
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
                val amount = amountInput.text.toDoubleOrNull()
                if (amount != null) {
                    onAddIncome(amount)
                    amountInput = TextFieldValue("")
                    keyboardController?.hide()
                }
            }) {
                Text(text = "Dodaj dochód")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Twoje Przychody: $income",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Preview Composable function to visualize the EarningsScreenViewContent
//@Preview(showBackground = true)
//@Composable
//fun PreviewEarningsScreenViewContent() {
//    val navController = rememberNavController()
//    val mockRepository = MockTransactionRepository()
//    EarningsScreenViewContent(
//        state = EarningsViewModel.EarningsScreenState(),
//        navController = navController
//    )
//}

// Dummy ViewModel and State for Preview purposes

