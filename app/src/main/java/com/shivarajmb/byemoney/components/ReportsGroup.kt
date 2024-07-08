package com.shivarajmb.byemoney.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivarajmb.byemoney.ViewModels.ReportGroupViewModel
import com.shivarajmb.byemoney.ViewModels.viewModelFactory
import com.shivarajmb.byemoney.components.charts.MonthlyChart
import com.shivarajmb.byemoney.components.charts.WeeklyChart
import com.shivarajmb.byemoney.components.charts.YearlyChart
import com.shivarajmb.byemoney.models.Recurrence
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.Typography
import com.shivarajmb.byemoney.utils.formatDayForRange
import java.text.DecimalFormat
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun reportGroup(
    innerPadding:PaddingValues,
    page:Int,
    recurrence:Recurrence,
    viewM: ReportGroupViewModel= viewModel(key = "$page-${recurrence.name}",
        factory = viewModelFactory {
        ReportGroupViewModel(page,recurrence)
    })
){

    val uiState=viewM.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    "${
                        uiState.startDate.formatDayForRange()
                    } - ${uiState.endDate.formatDayForRange()}",
                    style = Typography.titleSmall
                )
                Row(modifier = Modifier.padding(top = 4.dp)) {
                    Text(
                        "USD",
                        style = Typography.bodyMedium,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(DecimalFormat("0.#").format(uiState.totalInRange), style = Typography.headlineMedium)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Avg/day", style = Typography.titleSmall)
                Row(modifier = Modifier.padding(top = 4.dp)) {
                    Text(
                        "USD",
                        style = Typography.bodyMedium,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(DecimalFormat("0.#").format(uiState.avgPerDay), style = Typography.headlineMedium)
                }
            }
        }

        Box(
            modifier = Modifier
                .height(180.dp)
                .padding(vertical = 16.dp)
        ) {
            when (recurrence) {
                Recurrence.Weekly -> WeeklyChart(uiState.expenses)
                Recurrence.Monthly -> MonthlyChart(
                    uiState.expenses,
                    LocalDate.now()
                )
                Recurrence.Yearly -> YearlyChart(uiState.expenses)
                else -> Unit
            }
        }



        ExpenseList(
            expense = uiState.expenses,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    rememberScrollState()
                )

        )
    }
}