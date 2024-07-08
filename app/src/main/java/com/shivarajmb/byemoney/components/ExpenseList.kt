package com.shivarajmb.byemoney.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivarajmb.byemoney.models.Expense
import com.shivarajmb.byemoney.models.groupedByDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseList(expense:List<Expense>, modifier: Modifier=Modifier) {
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

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun ExpenseDayPreview() {
//    ByeMoneyTheme {
//        ExpenseList(expense = mockExpense)
//    }
//}

