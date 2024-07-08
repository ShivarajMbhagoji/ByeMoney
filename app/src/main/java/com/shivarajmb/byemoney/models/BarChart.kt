package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import java.time.DayOfWeek


@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun WeeklyChart(expenses: List<ExpenseList>) {
    val groupedExpenses = expenses.groupedByDayOfWeek()

    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = DayOfWeek.MONDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.TUESDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.WEDNESDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.THURSDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.FRIDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SATURDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SUNDAY.toString().substring(0,1),
                    value = 100f,
                    color = Color.Red
                )
            )

        ),
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        labelDrawer = SimpleValueDrawer()
    )


}