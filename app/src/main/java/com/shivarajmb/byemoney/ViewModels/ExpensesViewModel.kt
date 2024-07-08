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

data class ExpensesScreen(
    val recurrence: Recurrence=Recurrence.Daily,
    val sumTotal: Double=1299.32,
    val expenses:List<Expense> = listOf()
)

class ExpensesViewModel :ViewModel(){

    private val _uiState = MutableStateFlow(ExpensesScreen())
    val uiState:StateFlow<ExpensesScreen> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                expenses = db.query<Expense>().find()
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            setRecurrance(Recurrence.Daily)
        }
    }


    fun setRecurrance(recurrence: Recurrence){

        val (start,end)= calculateDateRange(recurrence,0)

        val filteredExpenses = db.query<Expense>().find().filter { expense ->
            (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                .isBefore(end)) || expense.date.toLocalDate()
                .isEqual(start) || expense.date.toLocalDate().isEqual(end)
        }

        val sumTotal = filteredExpenses.sumOf { it.amount }

        _uiState.update {state->
            state.copy(
                recurrence=recurrence,
                sumTotal=sumTotal,
                expenses = filteredExpenses
            )
        }
    }
}