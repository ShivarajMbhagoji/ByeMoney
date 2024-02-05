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
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.models.groupedByDayOfMonth
import com.shivarajmb.byemoney.ui.theme.LabelSecondary
import java.time.LocalDate
import java.time.YearMonth

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun monthlyChart(expense:List<ExpenseList>, month:LocalDate){
    val groupedExpense=expense.groupedByDayOfMonth()
    val numberOfDays=YearMonth.of(month.year,month.month).lengthOfMonth()

    BarChart(
        barChartData = BarChartData(
            bars= buildList (){
                for(i in 1..numberOfDays){
                    add(BarChartData.Bar(
                        label = "$i",
                        value = groupedExpense[i]?.total?.toFloat() ?: 0f,
                        color = Color.White
                    ))
                }
            }
        ),
        modifier = Modifier.height(157.dp).fillMaxWidth(),
        animation = simpleChartAnimation(),
        barDrawer = BarDrawer(Recurrance.Monthly),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = LabelSecondary
        ),
        labelDrawer = labelDrawer(Recurrance.Monthly,numberOfDays)
    )
}