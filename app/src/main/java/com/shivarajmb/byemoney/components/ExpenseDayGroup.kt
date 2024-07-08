package com.shivarajmb.byemoney.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivarajmb.byemoney.models.DayExpense
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.Typography
import com.shivarajmb.byemoney.utils.formatDay
import java.text.DecimalFormat
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseDayGroup(
    day:LocalDate,
    dayExpense: DayExpense,
    modifier: Modifier=Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
                day.formatDay(),
                style = Typography.headlineMedium,
                color = LabelSecondary
        )
        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))
        dayExpense.expenses.forEach { expense->
            ExpenseRow(expense = expense,modifier=Modifier.padding(top=12.dp))
        }
        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total:", style = Typography.bodyMedium, color = LabelSecondary)
            Text(
                DecimalFormat("USD 0.#").format(dayExpense.total),
                style = Typography.headlineMedium,
                color = LabelSecondary
            )
        }
    }
}

