package com.example.livingcostapp.presentation.mainScreen.savings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.LivingCostApp.R
import com.example.livingcostapp.mock.MockTransactionRepository
import com.example.livingcostapp.presentation.utils.string
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SavingsScreenView(
    state: SavingsViewModel.SavingsScreenState,
    navController: NavHostController,
    totalSavings: StateFlow<Double>,
    viewModel: SavingsViewModel,
) {
    EarningsScreenViewContent(state, navController, totalSavings, viewModel
    )
}

@Composable
fun EarningsScreenViewContent(
    state: SavingsViewModel.SavingsScreenState,
    navController: NavHostController,
    totalSavings: StateFlow<Double>,
    viewModel: SavingsViewModel
) {
    var amountInput by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val mockRepository = MockTransactionRepository()
    val savings by viewModel.totalSavings.collectAsState()


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
            Text(text = string(id = R.string.savings))
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = string(id = R.string.savings_label))
            }
            Row {
                Text(
                    text = string(
                        R.string.your_savings
                    ) + savings,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}
