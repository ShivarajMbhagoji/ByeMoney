package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivarajmb.byemoney.components.db
import com.shivarajmb.byemoney.models.daysRangeCalculator
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.mockExpense
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExpensesScreen(
    val recurrence: Recurrance=Recurrance.Daily,
    val sumTotal: Double=1299.32,
    val expenses:List<ExpenseList> = listOf()
)

class ExpensesViewModel :ViewModel(){

    private val _uiState = MutableStateFlow(ExpensesScreen())
    val uiState:StateFlow<ExpensesScreen> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                expenses = db.query<ExpenseList>().find()
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            setRecurrance(Recurrance.Daily)
        }
    }


    fun setRecurrance(recurrance: Recurrance){

        val (start,end)= daysRangeCalculator(recurrance,0)

        val filteredExpenses = db.query<ExpenseList>().find().filter { expense ->
            (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                .isBefore(end)) || expense.date.toLocalDate()
                .isEqual(start) || expense.date.toLocalDate().isEqual(end)
        }

        val sumTotal = filteredExpenses.sumOf { it.amount }

        _uiState.update {state->
            state.copy(
                recurrence=recurrance,
                sumTotal=sumTotal,
                expenses = filteredExpenses
            )
        }
    }
}