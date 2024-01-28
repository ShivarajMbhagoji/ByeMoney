@file:OptIn(ExperimentalMaterial3Api::class)

package com.shivarajmb.byemoney.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.ViewModels.ExpensesViewModel
import com.shivarajmb.byemoney.components.periodPicker
import com.shivarajmb.byemoney.models.ExpenseDayList
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.mockExpense
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground
import com.shivarajmb.byemoney.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController,viewM:ExpensesViewModel= viewModel()){

    val recurrences = listOf(
        Recurrance.Daily,
        Recurrance.Weekly,
        Recurrance.Monthly,
        Recurrance.Yearly
    )

    val state by viewM.uiState.collectAsState()

    var recurranceMenuOpened by remember {
        mutableStateOf(false)
    }


    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                )
            )
        },
        content = {innerPadding->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total for:",

                    )
                    periodPicker(
                        label = state.recurrance.target,
                        onClick = { recurranceMenuOpened=!recurranceMenuOpened },
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    DropdownMenu(
                        expanded =recurranceMenuOpened,
                        onDismissRequest = { recurranceMenuOpened=false }
                    ) {
                        recurrences.forEach{recurrance->
                            DropdownMenuItem(
                                text = {Text(recurrance.target)},
                                onClick = {
                                    viewM.setRecurrance(recurrance)
                                    recurranceMenuOpened=!recurranceMenuOpened
                                }
                            )
                        }
                    }
                }
                Row(
                    modifier=Modifier.padding(vertical = 32.dp)
                ) {
                    Text(
                        text = "$",
                        style = Typography.titleSmall,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp,top=4.dp)
                    )
                    Text(
                        "${state.sumTotal}",
                        style = Typography.titleLarge
                    )
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
@Preview(showBackground = true)
@Composable
fun ExpensesPreview(){
    ByeMoneyTheme {
        Expenses(navController = rememberNavController())
    }
}