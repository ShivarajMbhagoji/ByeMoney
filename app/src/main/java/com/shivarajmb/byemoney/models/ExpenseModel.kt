package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime

data class ExpenseList(
    val id: Int,
    val amount: Int,
    val recurrence: Recurrance,
    val date: LocalDateTime,
    val note: String?,
    val category: Category,
)

data class DayExpense(
    val expenses:MutableList<ExpenseList>,
    var total:Double
)

@RequiresApi(Build.VERSION_CODES.O)
fun List<ExpenseList>.groupedByDay():MutableMap<LocalDate,DayExpense>{
    val dataMap:MutableMap<LocalDate,DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val date=expense.date.toLocalDate()

        if(dataMap[date]==null){
            dataMap[date]= DayExpense(
                mutableListOf(),
                0.0
            )
        }

        dataMap[date]!!.expenses.add(expense)
        dataMap[date]!!.total=dataMap[date]!!.total.plus(expense.amount)
    }

    dataMap.values.forEach { dayExpenses ->
        dayExpenses.expenses.sortBy { expense -> expense.date }
    }

    return dataMap.toSortedMap(compareByDescending{it})
}