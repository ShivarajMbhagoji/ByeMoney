package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import com.shivarajmb.byemoney.models.Recurrance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class reportScreen(
        val recurrance:Recurrance=Recurrance.Weekly,
        val recurranceMenuOpened:Boolean=false
        )
class reportScreenViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(reportScreen())
    val uiState: StateFlow<reportScreen> = _uiState.asStateFlow()

    fun setRecurrance(recurrance: Recurrance){
        _uiState.update { current->
            current.copy(
                recurrance=recurrance
            )
        }
    }

    fun openRecurrenceMenu(){
        _uiState.update { currentState ->
            currentState.copy(
                recurranceMenuOpened = true
            )
        }
    }

    fun closeRecurrenceMenu(){
        _uiState.update { currentState ->
            currentState.copy(
                recurranceMenuOpened = false
            )
        }
    }
}