package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivarajmb.byemoney.db
import com.shivarajmb.byemoney.models.Expense
import com.shivarajmb.byemoney.models.Recurrence
import com.shivarajmb.byemoney.utils.calculateDateRange
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
    val expenses: List<Expense> = listOf(),
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val avgPerDay: Double = 0.0,
    val totalInRange: Double = 0.0
)

class ReportGroupViewModel(private val page:Int,val recurrence: Recurrence):ViewModel(){

    private val _uiState = MutableStateFlow(State())

    val uiState: StateFlow<State> = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO){
            val (start, end, daysInRange) = calculateDateRange(recurrence, page)

            val filteredExpense= db.query<Expense>().find().filter { expense ->
                (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate().isBefore(end)) ||
                        expense.date.toLocalDate()
                            .isEqual(start) || expense.date.toLocalDate().isEqual(end)

            }

            val totalExpense=filteredExpense.sumOf{it.amount}
            val avgPerDay: Double = totalExpense / daysInRange

            viewModelScope.launch(Dispatchers.Main){
                _uiState.update { currentState ->
                    currentState.copy(
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