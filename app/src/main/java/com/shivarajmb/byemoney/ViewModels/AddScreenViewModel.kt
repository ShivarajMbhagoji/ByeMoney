package com.shivarajmb.byemoney.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivarajmb.byemoney.components.db
import com.shivarajmb.byemoney.models.Category
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

data class AddScreen(
    val amount:String="",
    val recurrance:Recurrance=Recurrance.None,
    val date:LocalDate=LocalDate.now(),
    val note: String = "",
    val category: Category?=null,
    val categories: RealmResults<Category>? = null
)

class AddScreenViewModel:ViewModel() {
    private val _State= MutableStateFlow(AddScreen())
    val uiState:StateFlow<AddScreen> = _State.asStateFlow()


    init {
        _State.update { currentState ->
            currentState.copy(
                categories = db.query<Category>().find()
            )
        }
    }

    fun setAmount(amount: String) {
        var parsed = amount.toDoubleOrNull()

        if (amount.isEmpty()) {
            parsed = 0.0
        }

        if (parsed != null) {
            _State.update { currentState ->
                currentState.copy(
                    amount = amount.trim().ifEmpty { "0" },
                )
            }
        }
    }

    fun setRecurrance(recurrance: Recurrance) {
            _State.update { currentState ->
                currentState.copy(
                    recurrance = recurrance
                )
            }
    }

    fun setDate(date: LocalDate) {
        _State.update { currentState ->
            currentState.copy(
                date = date,)
        }
    }

    fun setNote(note: String) {
            _State.update { currentState ->
                currentState.copy(
                    note = note,
                )
            }
    }



    fun setCategory(category: Category) {
            _State.update { currentState ->
                currentState.copy(
                    category = category,
                )
            }
    }

    fun submitExpense() {
            if (_State.value.category != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    val now = LocalDateTime.now()
                    db.write {
                        this.copyToRealm(
                            ExpenseList(
                                _State.value.amount.toDouble(),
                                _State.value.recurrance,
                                _State.value.date.atTime(now.hour, now.minute, now.second),
                                _State.value.note,
                                this.query<Category>("_id == $0", _State.value.category!!._id)
                                    .find().first(),
                            )
                        )
                    }
                    _State.update { currentState ->
                        currentState.copy(
                            amount = "",
                            recurrance = Recurrance.None,
                            date = LocalDate.now(),
                            note = "",
                            category = null,
                            categories = null
                        )
                    }
                }
            }
    }



}