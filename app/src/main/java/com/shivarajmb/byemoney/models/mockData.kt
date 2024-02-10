package com.shivarajmb.byemoney.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random


val mockCategories = listOf(
    Category(
        "Bills",
        Color(
            Random.nextInt(0, 255),
            Random.nextInt(0, 255),
            Random.nextInt(0, 255)
        )
    ),
    Category(
        "Subscriptions", Color(
            Random.nextInt(0, 255),
            Random.nextInt(0, 255),
            Random.nextInt(0, 255)
        )
    ),
    Category(
        "Take out", Color(
            Random.nextInt(0, 255),
            Random.nextInt(0, 255),
            Random.nextInt(0, 255)
        )
    ),
    Category(
        "Hobbies", Color(
            Random.nextInt(0, 255),
            Random.nextInt(0, 255),
            Random.nextInt(0, 255)
        )
    ),
)

@RequiresApi(Build.VERSION_CODES.O)
val mockExpense:List<ExpenseList> = List(10){ index->
    ExpenseList(

//        amount= Random.nextDouble(1.0,999.0),
//        recurrance = listOf(
//            Recurrance.Daily,
//            Recurrance.None,
//            Recurrance.Monthly,
//            Recurrance.Yearly,
//            Recurrance.Weekly
//        ).random(),
//        date = LocalDateTime.now().minus(
//            Random.nextInt(300, 345600).toLong(),
//            ChronoUnit.SECONDS
//        ),
//        note= listOf(
//            "Time",
//            "Pass",
//            "Data",
//            "Money",
//            "Gandu",
//            "Namman",
//            "Snacks",
//            "Fuck",
//            "Juice"
//        ).random(),
//        category = mockCategories.random()
    )
}