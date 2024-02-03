package com.shivarajmb.byemoney

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.pages.Add
import com.shivarajmb.byemoney.pages.Categories
import com.shivarajmb.byemoney.pages.Expenses
import com.shivarajmb.byemoney.pages.Report
import com.shivarajmb.byemoney.pages.Settings
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ByeMoneyTheme {
                var showBottomBar by rememberSaveable {
                    mutableStateOf(true)
                }
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar=when(backStackEntry?.destination?.route){
                    "settings/categories" -> false
                    else -> true
                }

                Scaffold(
                    bottomBar = {
                        if(showBottomBar) {
                            NavigationBar(containerColor = TopAppBarBackground) {
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses") },
                                    label = {
                                        Text("Expenses")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.upload),
                                            contentDescription = "Upload"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports") },
                                    label = {
                                        Text("Reports")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.bar_chart),
                                            contentDescription = "Reports"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "add",
                                    onClick = { navController.navigate("add") },
                                    label = {
                                        Text("Add")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.add),
                                            contentDescription = "Add"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "settings",
                                    onClick = { navController.navigate("settings") },
                                    label = {
                                        Text("Settings")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.settings_outlined),
                                            contentDescription = "Settings"
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "expenses") {
                        composable("expenses") {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            ) {
                                Expenses(navController)
                            }
                        }
                        composable("reports") {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            ) {
                                Report(navController)
                            }
                        }
                        composable("Add") {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            ) {
                                Add(navController)
                            }
                        }
                        composable("settings") {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            ) {
                                Settings(navController)
                            }
                        }
                        composable("settings/categories") {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            ) {
                                Categories(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ByeMoneyTheme {
        Greeting("Android")
    }
}