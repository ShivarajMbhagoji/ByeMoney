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
import com.shivarajmb.byemoney.models.groupedByMonth
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import java.time.Month

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun yearlyChart(expense:List<ExpenseList>){
    val groupedExpense=expense.groupedByMonth()


    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = Month.JANUARY.name.substring(0, 1),
                    value = groupedExpense[Month.JANUARY.name]?.total?.toFloat()
                        ?: 0f,
                    color = Color.White,
                ),
                BarChartData.Bar(
                    label = Month.FEBRUARY.name.substring(0, 1),
                    value = groupedExpense[Month.FEBRUARY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.MARCH.name.substring(0, 1),
                    value = groupedExpense[Month.MARCH.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.APRIL.name.substring(0, 1),
                    value = groupedExpense[Month.APRIL.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.MAY.name.substring(0, 1),
                    value = groupedExpense[Month.MAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.JUNE.name.substring(0, 1),
                    value = groupedExpense[Month.JUNE.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.JULY.name.substring(0, 1),
                    value = groupedExpense[Month.JULY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.AUGUST.name.substring(0, 1),
                    value = groupedExpense[Month.AUGUST.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.SEPTEMBER.name.substring(0, 1),
                    value = groupedExpense[Month.SEPTEMBER.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.OCTOBER.name.substring(0, 1),
                    value = groupedExpense[Month.OCTOBER.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.NOVEMBER.name.substring(0, 1),
                    value = groupedExpense[Month.NOVEMBER.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = Month.DECEMBER.name.substring(0, 1),
                    value = groupedExpense[Month.DECEMBER.name]?.total?.toFloat() ?: 0f,
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