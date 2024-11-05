package com.example.livingcostapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livingcostapp.data.repository.TransactionDatabase
import com.example.livingcostapp.domain.repository.TransactionRepository
import com.example.livingcostapp.presentation.login.LoginScreenView
import com.example.livingcostapp.presentation.login.LoginUiAction
import com.example.livingcostapp.presentation.login.LoginViewModel
import com.example.livingcostapp.presentation.mainScreen.MainNavigationTarget
import com.example.livingcostapp.presentation.mainScreen.MainScreenView
import com.example.livingcostapp.presentation.mainScreen.MainScreenViewModel
import com.example.livingcostapp.presentation.mainScreen.MainUiAction
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsScreenView
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsUiAction
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsViewModel
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsViewModelFactory
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesScreenView
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesUIAction
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesViewModel
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesViewModelFactory
import com.example.livingcostapp.presentation.mainScreen.savings.SavingsScreenView
import com.example.livingcostapp.presentation.welcome.WelcomeScreenView
import com.example.livingcostapp.presentation.welcome.WelcomeUiAction
import com.example.livingcostapp.presentation.welcome.WelcomeViewModel
import com.example.livingcostapp.ui.theme.LivingCostAppTheme

class MainActivity : ComponentActivity() {


    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var earningsViewModel: EarningsViewModel
    private lateinit var repository: TransactionRepository
    private lateinit var mainScreenViewModel: MainScreenViewModel
    private lateinit var expensesViewModel: ExpensesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transactionDao = TransactionDatabase.getDatabase(applicationContext).transactionDao()
        repository = TransactionRepository(transactionDao)


        val mainScreenFactory = MainScreenViewModelFactory(repository)
        mainScreenViewModel =
            ViewModelProvider(this, mainScreenFactory)[MainScreenViewModel::class.java]

        val earningsFactory = EarningsViewModelFactory(repository)
        earningsViewModel =
            ViewModelProvider(this, earningsFactory)[EarningsViewModel::class.java]

        val expensesFactory = ExpensesViewModelFactory(repository)
        expensesViewModel =
            ViewModelProvider(this, expensesFactory)[ExpensesViewModel::class.java]

        setContent {
            LivingCostAppTheme {
                val navController = rememberNavController()
                MyApp {
                    AppNavigator(
                        navController,
                        welcomeViewModel,
                        loginViewModel,
                        mainScreenViewModel,
                        earningsViewModel,
                        expensesViewModel
                    )
                }
            }
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            Surface {
                content()
            }
        }
    }

    @Composable
    fun AppNavigator(
        navController: NavHostController,
        welcomeViewModel: WelcomeViewModel,
        loginViewModel: LoginViewModel,
        mainScreenViewModel: MainScreenViewModel,
        earningsViewModel: EarningsViewModel,
        expensesViewModel: ExpensesViewModel
    ) {

        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") {
                val state by welcomeViewModel.state.collectAsState()

                WelcomeScreenView(state = state, onLoginClick = {
                    welcomeViewModel.handleAction(WelcomeUiAction.LoginClick)
                })

                LaunchedEffect(state.navigateToLogin) {
                    if (state.navigateToLogin) {
                        navController.navigate("login")
                    }
                }
            }
            composable("login") {
                val state by loginViewModel.state.collectAsState()
                LoginScreenView(state = state, onLoginClick = {
                    loginViewModel.handleAction(LoginUiAction.NavigateToMain)
                })
                LaunchedEffect(state) {
                    if (state.navigateToMain) {
                        navController.navigate("main") {
                            popUpTo("welcome") { inclusive = true }
                        }

                    }
                }
            }
            composable("main") {
                val state by mainScreenViewModel.state.collectAsState()
                val totalIncome = earningsViewModel.totalIncome
                MainScreenView(
                    state = state,
                    onNavigateToEarnings = {
                        mainScreenViewModel.handleAction(MainUiAction.NavigateToEarnings)
                    },
                    onNavigateToExpenses = {
                        mainScreenViewModel.handleAction(MainUiAction.NavigateToExpenses)
                    },
                    onNavigateToSavings = {
                        mainScreenViewModel.handleAction(MainUiAction.NavigateToSavings)
                    },
                    totalIncome = totalIncome
                )
                LaunchedEffect(state) {
                    when (state.navigationTarget) {
                        MainNavigationTarget.Earnings -> navController.navigate("earnings") {
                            mainScreenViewModel.resetNavigation()
                        }

                        MainNavigationTarget.Expenses -> navController.navigate("expenses") {
                            mainScreenViewModel.resetNavigation()
                        }
                        MainNavigationTarget.Savings -> navController.navigate("savings")
                        else -> Unit
                    }
                }
            }
            composable("savings") {
                SavingsScreenView()
            }
            composable("expenses") {
                val state by expensesViewModel.state.collectAsState()
                val totalExpenses = expensesViewModel.totalExpenses
                ExpensesScreenView(
                    state = state,
                    navController = navController,
                    totalExpenses = totalExpenses,
                    onAddExpenses = { amount ->
                        expensesViewModel.handleAction(ExpensesUIAction.AddExpenses(amount))
                    })
            }
            composable("earnings") {
                val state by earningsViewModel.state.collectAsState()
                val totalIncome = earningsViewModel.totalIncome

                EarningsScreenView(
                    state = state,
                    navController = navController,
                    totalIncome = totalIncome,
                    onAddIncome = { amount ->
                        earningsViewModel.handleAction(EarningsUiAction.AddIncome(amount))
                    }
                )
            }
        }
    }
}


//    @Preview(showBackground = true)
//    @Composable
//    fun DefaultPreview() {
//        MyApp {
//            AppNavigator()
//        }
//    }
//    override fun onSupportNavigateUp(): Boolean {
//        // Obsługa przycisku cofania
//        val navHostFragment =
//            supportFragmen00tManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
//    }
