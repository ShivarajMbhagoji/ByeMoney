package com.shivarajmb.byemoney

import com.shivarajmb.byemoney.models.Category
import com.shivarajmb.byemoney.models.Expense
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.create(schema = setOf(Expense::class, Category::class))
val db: Realm = Realm.open(config)