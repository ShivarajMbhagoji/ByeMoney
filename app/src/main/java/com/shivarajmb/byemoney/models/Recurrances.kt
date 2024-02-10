package com.shivarajmb.byemoney.models

sealed class Recurrance(val name:String,val target:String){
    object None : Recurrance("None", "None")
    object Daily : Recurrance("Daily", "Day")
    object Weekly : Recurrance("Weekly", "Week")
    object Monthly : Recurrance("Monthly", "Month")
    object Yearly : Recurrance("Yearly", "Year")
}

fun String.toRecurrance(): Recurrance {
    return when(this) {
        "None" -> Recurrance.None
        "Daily" -> Recurrance.Daily
        "Weekly" -> Recurrance.Weekly
        "Monthly" -> Recurrance.Monthly
        "Yearly" -> Recurrance.Yearly
        else -> Recurrance.None
    }
}