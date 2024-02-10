package com.nikolovlazar.goodbyemoney.mock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color

import com.shivarajmb.byemoney.models.Category
import com.shivarajmb.byemoney.models.ExpenseList
import com.shivarajmb.byemoney.models.Recurrance
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


val faker = Faker()

val mockCategories = listOf(
  Category(
    "Bills",
    Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Subscriptions", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Take out", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Hobbies", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
)

@RequiresApi(Build.VERSION_CODES.O)
val mockExpenses: List<ExpenseList> = List(30) { index ->
//  ExpenseList(
//    amount = faker.random.nextInt(min = 1, max = 999)
//      .toDouble() + faker.random.nextDouble(),
//    date = LocalDateTime.now().minus(
//      faker.random.nextInt(min = 300, max = 345600).toLong(),
//      ChronoUnit.SECONDS
//    ),
//    recurrance  = faker.random.randomValue(
//      listOf(
//          Recurrance.None,
//          Recurrance.Daily,
//          Recurrance.Monthly,
//          Recurrance.Weekly,
//          Recurrance.Yearly
//      )
//    ),
//    note = faker.australia.animals(),
//    category = faker.random.randomValue(mockCategories)
//  )
  ExpenseList()
}