package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

class ExpenseList(): RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var amount: Double = 0.0

    private var _recurrenceName: String = "None"
    val recurrence: Recurrance get() { return _recurrenceName.toRecurrance() }

    private var _dateValue: String = LocalDateTime.now().toString()
    val date: LocalDateTime get() { return LocalDateTime.parse(_dateValue) }

    var note: String = ""
    var category: Category? = null

    constructor(
        amount: Double,
        recurrence: Recurrance,
        date: LocalDateTime,
        note: String,
        category: Category,
    ) : this() {
        this.amount = amount
        this._recurrenceName = recurrence.name
        this._dateValue = date.toString()
        this.note = note
        this.category = category
    }
}


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

@RequiresApi(Build.VERSION_CODES.O)
fun List<ExpenseList>.groupedByDayOfWeek():MutableMap<String,DayExpense>{
    val dataMap:MutableMap<String,DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val dayOfWeek=expense.date.dayOfWeek

        if(dataMap[dayOfWeek.name]==null){
            dataMap[dayOfWeek.name]= DayExpense(
                mutableListOf(),
                0.0
            )
        }

        dataMap[dayOfWeek.name]!!.expenses.add(expense)
        dataMap[dayOfWeek.name]!!.total=dataMap[dayOfWeek.name]!!.total.plus(expense.amount)
    }

    return dataMap.toSortedMap(compareByDescending{it})
}


@RequiresApi(Build.VERSION_CODES.O)
fun List<ExpenseList>.groupedByDayOfMonth():MutableMap<Int,DayExpense>{
    val dataMap:MutableMap<Int,DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val dayOfMonth=expense.date.toLocalDate().dayOfMonth

        if(dataMap[dayOfMonth]==null){
            dataMap[dayOfMonth]= DayExpense(
                mutableListOf(),
                0.0
            )
        }

        dataMap[dayOfMonth]!!.expenses.add(expense)
        dataMap[dayOfMonth]!!.total=dataMap[dayOfMonth]!!.total.plus(expense.amount)
    }

    return dataMap.toSortedMap(compareByDescending{it})
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<ExpenseList>.groupedByMonth():MutableMap<String,DayExpense>{
    val dataMap:MutableMap<String,DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val month=expense.date.toLocalDate().month.name

        if(dataMap[month]==null){
            dataMap[month]= DayExpense(
                mutableListOf(),
                0.0
            )
        }

        dataMap[month]!!.expenses.add(expense)
        dataMap[month]!!.total=dataMap[month]!!.total.plus(expense.amount)
    }

    return dataMap.toSortedMap(compareByDescending{it})
}

