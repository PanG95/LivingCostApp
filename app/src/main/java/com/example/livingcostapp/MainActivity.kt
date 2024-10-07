package com.example.livingcostapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livingcostapp.presentation.login.LoginScreenView
import com.example.livingcostapp.presentation.mainScreen.MainScreenView
import com.example.livingcostapp.presentation.mainScreen.earnings.EarningsScreenView
import com.example.livingcostapp.presentation.mainScreen.expenses.ExpensesScreenView
import com.example.livingcostapp.presentation.mainScreen.savings.SavingsScreenView
import com.example.livingcostapp.presentation.welcome.WelcomeScreenView
import com.example.livingcostapp.ui.theme.LivingCostAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LivingCostAppTheme {
                setContent {
                    MyApp {
                        AppNavigator()
                    }
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
    fun AppNavigator() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") {
                WelcomeScreenView(onLoginClick = {
                    navController.navigate("login")
                })
            }
            composable("login") {
                LoginScreenView(onLoginClick = {
                    navController.navigate("main")
                })
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


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApp {
            AppNavigator()
        }
    }
//    override fun onSupportNavigateUp(): Boolean {
//        // Obsługa przycisku cofania
//        val navHostFragment =
//            supportFragmen00tManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
//    }
}