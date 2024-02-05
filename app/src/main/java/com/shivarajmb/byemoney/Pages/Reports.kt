package com.shivarajmb.byemoney.pages

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.shivarajmb.byemoney.ViewModels.reportScreenViewModel
import com.shivarajmb.byemoney.components.reportGroup
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Report(viewM: reportScreenViewModel = viewModel()) {


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
            val pages=when(state.recurrance){
                Recurrance.Weekly->53
                Recurrance.Monthly->12
                Recurrance.Yearly->1
                else->53
            }
            HorizontalPager(count = pages, reverseLayout = true) {page->
                reportGroup(innerPadding = innerPadding,page = page, recurrance = state.recurrance)

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ReportPreview() {
    ByeMoneyTheme {
        Report()
    }
}