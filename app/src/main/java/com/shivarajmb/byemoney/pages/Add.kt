package com.shivarajmb.byemoney.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.ViewModels.AddScreenViewModel
import com.shivarajmb.byemoney.components.TableRow
import com.shivarajmb.byemoney.components.UnstyledTextField
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.ui.theme.BackgroundElevated
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.DividerColour
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground
import com.shivarajmb.byemoney.ui.theme.Typography
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.shivarajmb.byemoney.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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



    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Add") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
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
                    TableRow(label = "Amount", detailContent = {
                        UnstyledTextField(
                            value = state.amount,
                            onValueChange = viewM::setAmount,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("0") },
                            arrangement = Arrangement.End,
                            maxLines = 1,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            )
                        )
                    })
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow(label = "Recurrence", detailContent = {
                        var recurrenceMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        TextButton(
                            onClick = { recurrenceMenuOpened = true }, shape = Shapes.large
                        ) {
                            Text(state.recurrance?.name ?: Recurrance.None.name)
                            DropdownMenu(expanded = recurrenceMenuOpened,
                                onDismissRequest = { recurrenceMenuOpened = false }) {
                                recurrences.forEach { recurrence ->
                                    DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                                        viewM.setRecurrance(recurrence)
                                        recurrenceMenuOpened = false
                                    })
                                }
                            }
                        }
                    })

                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)

                    var datePickerShowing by remember {
                        mutableStateOf(false)
                    }
                    TableRow(label = "Date", detailContent = {
                        TextButton(onClick = { datePickerShowing = true }) {
                            Text(state.date.toString())
                        }
                        if (datePickerShowing) {
                            DatePickerDialog(onDismissRequest = { datePickerShowing = false },
                                onDateChange = { it ->
                                    viewM.setDate(it)
                                    datePickerShowing = false
                                },
                                initialDate = state.date,
                                title = { Text("Select date", style = Typography.titleLarge) })
                        }
                    })
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow(label = "Note", detailContent = {
                        UnstyledTextField(
                            value = state.note,
                            placeholder = { Text("Leave some notes") },
                            arrangement = Arrangement.End,
                            onValueChange = viewM::setNote,
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                        )
                    })
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)


                    TableRow(label = "Category", detailContent = {
                        var categoriesMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        TextButton(
                            onClick = { categoriesMenuOpened = true }, shape = Shapes.large
                        ) {
                            Text(
                                state.category?.name ?: "Select a category first",
                                color = state.category?.color ?: Color.White
                            )
                            DropdownMenu(expanded = categoriesMenuOpened,
                                onDismissRequest = { categoriesMenuOpened = false }) {
                                state.categories?.forEach { category ->
                                    DropdownMenuItem(text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Surface(
                                                modifier = Modifier.size(10.dp),
                                                shape = CircleShape,
                                                color = category.color
                                            ) {}
                                            Text(
                                                category.name, modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }, onClick = {
                                        viewM.setCategory(category)
                                        categoriesMenuOpened = false
                                    })
                                }
                            }
                        }
                    })

                    Button(
                        onClick = viewM::submitExpense,
                        modifier = Modifier.padding(16.dp),
                        shape = Shapes.large,
                        enabled = state.category != null
                    ) {
                        Text("Submit expense")
                    }


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
