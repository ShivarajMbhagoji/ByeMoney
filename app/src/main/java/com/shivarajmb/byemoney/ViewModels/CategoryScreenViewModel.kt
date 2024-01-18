package com.shivarajmb.byemoney.ViewModels


import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.shivarajmb.byemoney.Models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class categoryScreen(
    val newCategoryColor:Color= Color.White,
    val newCategoryName:String="",
    val colorPickerShoeing:Boolean=false,
    val categoryList:MutableList<Category> = mutableListOf(
        Category("Gas", Color.Yellow),
        Category("Rent",Color.DarkGray)
    )
)

class CategoryViewModel:ViewModel(){
    val _uiState= MutableStateFlow(categoryScreen())
    val uiState:StateFlow<categoryScreen> = _uiState.asStateFlow()


}