package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import com.shivarajmb.byemoney.models.Recurrance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AddScreen(
    val amount:String="",
    val recurrance:Recurrance?=null,
    val date:LocalDate=LocalDate.now(),
    val note: String = "",
    val category: String? = null, // TODO: Replace when you build the Category model
)

class AddScreenViewModel:ViewModel() {
    private val _State= MutableStateFlow(AddScreen())
    val uiState:StateFlow<AddScreen> = _State.asStateFlow()

    fun setAmount(amount:String){
        _State.update { currentState->
            currentState.copy(
                amount=amount.trim(),
            )
        }
    }

    fun setRecurrance(recurrance: Recurrance){
        _State.update { currentState->
            currentState.copy(
                recurrance=recurrance
            )
        }
    }

    fun setDate(date: LocalDate) {
        _State.update {currentState ->
            currentState.copy(
                date = date,
            )
        }
    }

    fun setNote(note: String) {
        _State.update {currentState ->
            currentState.copy(
                note = note,
            )
        }
    }

    fun setCategory(category: String) { // TODO: replace String with actual Category class
        _State.update {currentState ->
            currentState.copy(
                category = category,
            )
        }
    }

    fun submitExpense() {
        // TODO: Save to local db
    }

}