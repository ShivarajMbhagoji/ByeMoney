package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDate
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