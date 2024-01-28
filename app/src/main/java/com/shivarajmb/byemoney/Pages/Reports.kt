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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shivarajmb.byemoney.ViewModels.ExpensesViewModel
import com.shivarajmb.byemoney.components.periodPicker
import com.shivarajmb.byemoney.models.ExpenseDayList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.mockExpense
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground
import com.shivarajmb.byemoney.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Report(navController: NavController,viewM:ExpensesViewModel= viewModel()) {


    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                )
            )
        },
        content = { innerPadding ->
            Row(
                modifier = Modifier.padding(innerPadding),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        "12 sep-18sep",
                        style = Typography.titleLarge
                    )
                    Text(
                        "USD 85",
//                        style =
                    )
                }
            }
        }
    )
}