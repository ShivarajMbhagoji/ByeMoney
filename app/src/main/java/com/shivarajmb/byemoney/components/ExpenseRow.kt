package com.shivarajmb.byemoney.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shivarajmb.byemoney.models.Expense
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.Typography
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseRow(expense:Expense, modifier: Modifier=Modifier) {
    Column (
        modifier = modifier
    ){
        Row (
            modifier=modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                expense.note?:expense.category!!.name,
                style = Typography.headlineMedium
            )
            Text(
                "USD ${DecimalFormat("0.#").format(expense.amount)}",
                style = Typography.headlineMedium
            )
        }
        Row (
            modifier=modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CategoryBadge(category = expense.category!!)
            Text(expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = Typography.bodyMedium,
                color= LabelSecondary
            )
        }
    }
}

