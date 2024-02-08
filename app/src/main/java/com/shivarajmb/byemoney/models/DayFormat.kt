package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocalDate.dayFormat():String {
    val today=LocalDate.now()
    val yesterday=LocalDate.now().minusDays(1)

    return when{
        this.isEqual(today) -> "Today"
        this.isEqual(yesterday) -> "Yesterday"
        this.year != today.year -> this.format(DateTimeFormatter.ofPattern("ddd, dd MMM yyyy"))
        else -> this.format(DateTimeFormatter.ofPattern("E, dd MMM"))
    }
}

fun LocalDateTime.dayRangeFormat():String{
    val today = LocalDateTime.now()
    val yesterday = today.minusDays(1)

    return when {
        this.year != today.year -> this.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        else -> this.format(DateTimeFormatter.ofPattern("dd MMM"))
    }
}

data class daysRangeData(
    val start:LocalDate,
    val end:LocalDate,
    val daysRange:Int
)

fun daysRangeCalculator(recurrance:Recurrance,page:Int):daysRangeData{

    val today=LocalDate.now()
    lateinit var start: LocalDate
    lateinit var end: LocalDate
    var daysRange=7
    when(recurrance){
        Recurrance.Daily->{
            start=LocalDate.now().minusDays(page.toLong())
            end=start
        }
        Recurrance.Weekly ->{
            start = LocalDate.now().minusDays(today.dayOfWeek.value.toLong() - 1)
                .minusDays((page * 7).toLong())
            end = start.plusDays(6)
            daysRange=7
        }

        Recurrance.Monthly-> {
            start =
                LocalDate.of(today.year, today.month, 1)
                    .minusMonths(page.toLong())
            val monthDays = YearMonth.of(start.year, start.month).lengthOfMonth()
            end = start.plusDays(monthDays.toLong())
            daysRange = monthDays
        }

        Recurrance.Yearly->{
            start = LocalDate.of(today.year, 1, 1)
            end = LocalDate.of(today.year, 12, 31)
            daysRange = 365
        }

        else->Unit
    }

    return daysRangeData(start,end,daysRange)
}