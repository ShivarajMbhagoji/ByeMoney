package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivarajmb.byemoney.components.db
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.daysRangeCalculator
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime

data class State (
    val expenses: List<ExpenseList> = listOf(),
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val avgPerDay: Double = 0.0,
    val totalInRange: Double = 0.0
)

class ReportGroupViewModel(val page:Int,val recurrance: Recurrance):ViewModel(){

    private val _uiState = MutableStateFlow(State())

    val uiState: StateFlow<State> = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO){
            val (start, end, daysInRange) = daysRangeCalculator(recurrance, page)

            val filteredExpense= db.query<ExpenseList>().find().filter {expense ->
                (expense.date.toLocalDate().isBefore(end)&&expense.date.toLocalDate().isAfter(start)) ||
                        expense.date.toLocalDate()
                            .isEqual(start) || expense.date.toLocalDate().isEqual(end)

            }

            val totalExpense=filteredExpense.sumOf{it.amount}
            val avgPerDay: Double = totalExpense / daysInRange

            viewModelScope.launch(Dispatchers.Main){
                _uiState.update { state ->
                    state.copy(
                        startDate = LocalDateTime.of(start, LocalTime.MIN),
                        endDate = LocalDateTime.of(end,LocalTime.MAX),
                        expenses = filteredExpense,
                        avgPerDay = avgPerDay,
                        totalInRange = totalExpense

                    )
                }
            }
        }
    }
}