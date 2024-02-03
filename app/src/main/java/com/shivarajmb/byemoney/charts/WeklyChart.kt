package com.shivarajmb.byemoney.charts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.groupedByDayOfWeek
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyChart(expenses: List<ExpenseList>) {
    val groupedExpenses = expenses.groupedByDayOfWeek()

    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = DayOfWeek.MONDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.MONDAY.name]?.total?.toFloat()
                        ?: 0f,
                    color = Color.White,
                ),
                BarChartData.Bar(
                    label = DayOfWeek.TUESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.TUESDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.WEDNESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.WEDNESDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.THURSDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.FRIDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.FRIDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SATURDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.SATURDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SUNDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.SUNDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),

            )
        ),
        modifier = Modifier.height(157.dp).fillMaxWidth(),
        animation = simpleChartAnimation(),
        barDrawer = BarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = LabelSecondary
        ),
        labelDrawer = labelDrawer()
    )
}