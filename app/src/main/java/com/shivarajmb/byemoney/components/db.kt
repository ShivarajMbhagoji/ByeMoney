package com.shivarajmb.byemoney.components

import com.shivarajmb.byemoney.models.Category
import com.shivarajmb.byemoney.models.ExpenseList
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.create(schema = setOf(ExpenseList::class, Category::class))
val db: Realm = Realm.open(config)