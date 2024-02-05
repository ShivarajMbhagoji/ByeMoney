package com.shivarajmb.byemoney.ViewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolovlazar.goodbyemoney.mock.mockExpenses
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

data class State @RequiresApi(Build.VERSION_CODES.O) constructor(
    val expenses: List<ExpenseList> = mockExpenses,
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val avgPerDay: Double = 0.0,
    val totalInRange: Double = 0.0
)

class ReportGroupViewModel(val page:Int,val recurrance: Recurrance):ViewModel(){
    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(State())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private lateinit var start:LocalDate
    private lateinit var end:LocalDate
    private var daysRange:Int=1

    init {
        viewModelScope.launch(Dispatchers.IO){
            val today=LocalDate.now()
            when(recurrance){
                Recurrance.Weekly ->{
                    start = LocalDate.now().minusDays(today.dayOfWeek.value.toLong() - 1)
                        .minusDays((page * 7).toLong())
                    end = start.plusDays(6)
                    daysRange=7
                }

                Recurrance.Monthly-> {
                    start =
                        LocalDate.of(today.year, today.month, 1)
                            .minusMonths(page.toLong())
                    val monthDays = YearMonth.of(start.year, start.month).lengthOfMonth()
                    end = start.plusDays(monthDays.toLong())
                    daysRange = monthDays
                }

                Recurrance.Yearly->{
                    start = LocalDate.of(today.year, 1, 1)
                    end = LocalDate.of(today.year, 12, 31)
                    daysRange = 365
                }

                else->Unit
            }

            val filteredExpense= mockExpenses.filter {expense ->
                (expense.date.toLocalDate().isBefore(end)&&expense.date.toLocalDate().isAfter(start)) ||
                        expense.date.toLocalDate()
                            .isEqual(start) || expense.date.toLocalDate().isEqual(end)

            }

            val totalExpense=filteredExpense.sumOf{it.amount}

            viewModelScope.launch(Dispatchers.Main){
                _uiState.update { state ->
                    state.copy(
                        startDate = LocalDateTime.of(start, LocalTime.MIN),
                        endDate = LocalDateTime.of(end,LocalTime.MAX),
                        expenses = filteredExpense,
                        avgPerDay = totalExpense/daysRange,
                        totalInRange = totalExpense

                    )
                }
            }
        }
    }
}