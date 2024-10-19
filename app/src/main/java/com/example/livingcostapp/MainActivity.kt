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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livingcostapp.presentation.login.LoginScreenView
import com.example.livingcostapp.presentation.login.LoginUiAction
import com.example.livingcostapp.presentation.login.LoginViewModel
import com.example.livingcostapp.presentation.mainScreen.MainScreenView
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsScreenView
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesScreenView
import com.example.livingcostapp.presentation.mainScreen.savings.SavingsScreenView
import com.example.livingcostapp.presentation.welcome.WelcomeScreenView
import com.example.livingcostapp.presentation.welcome.WelcomeUiAction
import com.example.livingcostapp.presentation.welcome.WelcomeViewModel
import com.example.livingcostapp.ui.theme.LivingCostAppTheme

class MainActivity : ComponentActivity() {

    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivingCostAppTheme {
                val navController = rememberNavController()
                MyApp {
                    AppNavigator(navController, welcomeViewModel, loginViewModel)
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
        loginViewModel: LoginViewModel
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
                        navController.navigate("main")
                    }
                }
            }
            composable("main") {
                MainScreenView(navController = navController)
            }
            composable("savings") {
                SavingsScreenView()
            }
            composable("expenses") {
                ExpensesScreenView()
            }
            composable("earnings") {
                EarningsScreenView()
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
}