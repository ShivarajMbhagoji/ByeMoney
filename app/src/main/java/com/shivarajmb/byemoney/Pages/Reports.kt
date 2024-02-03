package com.shivarajmb.byemoney.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.ViewModels.ExpensesViewModel
import com.shivarajmb.byemoney.ViewModels.reportScreenViewModel
import com.shivarajmb.byemoney.charts.WeeklyChart
import com.shivarajmb.byemoney.charts.monthlyChart
import com.shivarajmb.byemoney.charts.yearlyChart
import com.shivarajmb.byemoney.models.ExpenseDayList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.mockExpense
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground
import com.shivarajmb.byemoney.ui.theme.Typography
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun Report(navController: NavController,viewM:reportScreenViewModel= viewModel()) {


    val recurrences = listOf(

        Recurrance.Weekly,
        Recurrance.Monthly,
        Recurrance.Yearly
    )

    val state = viewM.uiState.collectAsState().value

    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                ),
                actions = {
                    IconButton(onClick = viewM::openRecurrenceMenu) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Recurrance menu"
                        )
                    }
                    DropdownMenu(
                        expanded = state.recurranceMenuOpened,
                        onDismissRequest = { viewM.closeRecurrenceMenu() }) {
                        recurrences.forEach { recurrance ->
                            DropdownMenuItem(
                                text = { Text(recurrance.name) },
                                onClick = {
                                    viewM.setRecurrance(recurrance)
                                    viewM.closeRecurrenceMenu()
                                }

                            )
                        }
                    }
                },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)

            ){
                Row(
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Column{
                        Text(
                            "12 sep-18sep",
                            style = Typography.titleLarge
                        )
                        Text(
                            "USD 85",
                          style = Typography.labelMedium
                        )
                    }

                    Column{
                        Text(
                            "Avg/Day",
                            style = Typography.titleLarge
                        )
                        Text(
                            "USD 85",
                            style = Typography.labelMedium
                        )
                    }
                }

                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    when(state.recurrance){
                        Recurrance.Weekly-> WeeklyChart(mockExpense)
                        Recurrance.Monthly-> monthlyChart(mockExpense, LocalDate.now() )
                        Recurrance.Yearly-> yearlyChart(mockExpense)
                        else->Unit
                    }
                }


                ExpenseDayList(
                    expense =mockExpense,
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )

                )
            }
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ReportsPreview() {
    ByeMoneyTheme {
        Report(navController = rememberNavController())
    }
}