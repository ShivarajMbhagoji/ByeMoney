package com.shivarajmb.byemoney.Pages

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.Components.TableRow
import com.shivarajmb.byemoney.Components.UnstyledTextField
import com.shivarajmb.byemoney.Models.Recurrance
import com.shivarajmb.byemoney.ViewModels.AddScreenViewModel
import com.shivarajmb.byemoney.ui.theme.BackgroundElevated
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.DividerColour
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController,viewM:AddScreenViewModel=viewModel()) {

    val state by viewM.uiState.collectAsState()

    val recurrences= listOf(
        Recurrance.None,
        Recurrance.Daily ,
        Recurrance.Weekly,
        Recurrance.Monthly,
        Recurrance.Yearly
    )

    val categories = listOf("Groceries", "Bills", "Hobbies", "Take out")

    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier
                    .clickable { }
                    .padding(16.dp)
                    .clip(Shapes.medium)
                    .background(BackgroundElevated)
                    .fillMaxWidth()
                ) {
                    TableRow("Amount"){
                        UnstyledTextField(
                            value = "waal",
                            onValueChange ={},
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            )
                        )
                    }
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Recurrance"){
                        var recurrenceMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        TextButton(
                            onClick = { recurrenceMenuOpened=true },
                            shape= Shapes.large
                            ) {
                                Text(state.recurrance?.name?:Recurrance.None.name)
                                DropdownMenu(
                                    expanded =recurrenceMenuOpened,
                                    onDismissRequest = { recurrenceMenuOpened=false }
                                ) {recurrences.forEach { recurrance ->
                                    DropdownMenuItem(
                                        text = { Text(text = recurrance.name) },
                                        onClick = {
                                            viewM.setRecurrance(recurrance)
                                            recurrenceMenuOpened=false
                                        }
                                    )
                                }

                            }

                        }
                    }

                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)

                    var datePickerShowing by remember {
                        mutableStateOf(false)
                    }
                    TableRow("Date") {
                        TextButton(onClick = { datePickerShowing = true }) {
                            Text(state.date.toString())
                        }
//                        if (datePickerShowing) {
//                            DatePickerDialog(
//                                onDismissRequest = { datePickerShowing = false },
//                                onDateChange = { it ->
//                                    viewM.setDate(it)
//                                    datePickerShowing = false
//                                },
//                                initialDate = state.date,
//                                title = {
//                                    Text("Select date",
//                                    style = Typography.titleLarge)
//                                }
//                            )
//                        }
                    }
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow(
                        "Note")
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow(
                        "Category")


                }
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun AddPreview(){
    val navController= rememberNavController()
    ByeMoneyTheme {
        Add(navController=navController)
    }
    
}
