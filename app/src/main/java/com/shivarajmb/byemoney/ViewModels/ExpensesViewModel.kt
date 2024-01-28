package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import com.shivarajmb.byemoney.models.Recurrance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ExpensesScreen(
    val recurrance: Recurrance=Recurrance.Daily,
    val sumTotal: Double=1299.32
)

class ExpensesViewModel :ViewModel(){

    private val _uiState = MutableStateFlow(ExpensesScreen())
    val uiState:StateFlow<ExpensesScreen> = _uiState.asStateFlow()

    fun setRecurrance(recurrance: Recurrance){
        _uiState.update {state->
            state.copy(
                recurrance=recurrance
            )
        }
    }
}