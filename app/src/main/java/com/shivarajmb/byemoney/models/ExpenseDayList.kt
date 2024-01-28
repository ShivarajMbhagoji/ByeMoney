package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivarajmb.byemoney.components.ExpenseDayGroup
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseDayList(expense:List<ExpenseList>,modifier: Modifier=Modifier) {
    val groupedExpense=expense.groupedByDay()

    Column(modifier = modifier) {
       groupedExpense.keys.forEach { date->
           if(groupedExpense[date]!=null){
               ExpenseDayGroup(
                   day = date, dayExpense = groupedExpense[date]!!,
                   modifier=Modifier.padding(top=24.dp)
               )
           }
       }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ExpenseDayPreview() {
    ByeMoneyTheme {
        ExpenseDayList(expense = mockExpense)
    }
}

