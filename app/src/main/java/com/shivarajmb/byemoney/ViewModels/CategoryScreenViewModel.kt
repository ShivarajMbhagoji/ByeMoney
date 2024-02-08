package com.shivarajmb.byemoney.ViewModels


import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.shivarajmb.byemoney.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class categoryScreen(
    val newCategoryColor:Color= Color.White,
    val newCategoryName:String="",
    val colorPickerShowing:Boolean=false,
    val categoryList:MutableList<Category> = mutableListOf(
        Category("Bills", Color.Red),
        Category("Subscriptions", Color.Yellow),
        Category("Take out", Color.Blue),
        Category("Hobbies", Color.Cyan),
    )
)

class CategoryViewModel:ViewModel(){
    val _uiState= MutableStateFlow(categoryScreen())
    val uiState:StateFlow<categoryScreen> = _uiState.asStateFlow()

    fun setNewCategoryName(name:String){
        _uiState.update {currentState->
            currentState.copy(
                newCategoryName = name
            )
        }
    }

    fun setNewCategoryColor(color:Color){
        _uiState.update { currentState->
            currentState.copy(
                newCategoryColor=color
            )
        }
    }

    fun showColorPicker(){
        _uiState.update { currentState->
            currentState.copy(
                colorPickerShowing = true
            )
        }
    }

    fun hideColorPicker(){
        _uiState.update { currentState->
            currentState.copy(
                colorPickerShowing = false
            )
        }
    }

    fun addNewCategory(){
        val newCategoryList:MutableList<Category> = mutableListOf(
            Category(_uiState.value.newCategoryName,_uiState.value.newCategoryColor)
        )

        newCategoryList.addAll(_uiState.value.categoryList)

        _uiState.update { currentScreen->
            currentScreen.copy(
                categoryList = newCategoryList,
                newCategoryName = "",
                newCategoryColor = Color.White
            )
        }
    }

    fun deleteCategory(category: Category) {
        val index = _uiState.value.categoryList.indexOf(category)
        val newList = mutableListOf<Category>()
        newList.addAll(_uiState.value.categoryList)
        newList.removeAt(index)

        _uiState.update { currentState ->
            currentState.copy(
                categoryList = newList
            )
        }
    }
}