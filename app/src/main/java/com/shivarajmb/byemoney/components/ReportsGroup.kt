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
import com.shivarajmb.byemoney.charts.monthlyChart
import com.shivarajmb.byemoney.charts.yearlyChart
import com.shivarajmb.byemoney.models.ExpenseDayList
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.dayRangeFormat
import com.shivarajmb.byemoney.models.mockExpense
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import com.shivarajmb.byemoney.ui.theme.Typography
import java.text.DecimalFormat
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun reportGroup(
    innerPadding:PaddingValues,
    page:Int,
    recurrance:Recurrance,
    viewM: ReportGroupViewModel= viewModel(key = "$page-${recurrance.name}",
        factory = viewModelFactory {
        ReportGroupViewModel(page,recurrance)
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
                        uiState.startDate.dayRangeFormat()
                    } - ${uiState.endDate.dayRangeFormat()}",
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
            when (recurrance) {
                Recurrance.Weekly -> com.shivarajmb.byemoney.charts.WeeklyChart(uiState.expenses)
                Recurrance.Monthly -> monthlyChart(
                    uiState.expenses,
                    LocalDate.now()
                )
                Recurrance.Yearly -> yearlyChart(uiState.expenses)
                else -> Unit
            }
        }



        ExpenseDayList(
            expense = mockExpense,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    rememberScrollState()
                )

        )
    }
}