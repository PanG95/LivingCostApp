package com.example.livingcostapp.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(navController: NavController) {

    val expensesState = remember { mutableStateOf(0) }
    val earningsState = remember { mutableStateOf(0) }
    val savingsState = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Nagłówek
                Text(
                    text = "Ekran Główny",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                // Boxes
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                        .background(Color.LightGray)
                        .clickable {
                            navController.navigate("expenses")
                            expensesState.value += 50
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Wydatki: ${expensesState.value} PLN", fontSize = 30.sp)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                        .background(Color.LightGray)
                        .clickable {
                            navController.navigate("earnings")
                            earningsState.value += 100
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Przychodz: ${earningsState.value}", fontSize = 30.sp)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray)
                        .clickable {
                            navController.navigate("savings")
                            savingsState.value += 200
                        },
                    contentAlignment = Alignment.Center

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Top
                    )

                    {
                        Text(
                            text = "Oszczędności",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .background(
                                    Color.Blue,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .align(Alignment.CenterHorizontally)
                                .padding(9.dp)
                        ) {
                            Text(
                                text = "${savingsState.value}",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.Center)

                            )
                        }
                    }
                }
            }
        })

}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreenView(navController = navController)
}