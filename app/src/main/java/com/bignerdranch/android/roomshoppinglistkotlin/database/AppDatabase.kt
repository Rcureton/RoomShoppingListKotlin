package com.bignerdranch.android.roomshoppinglistkotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(ShoppingItems::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoppingItemsDao(): ShoppingItemsDao

}
