package com.bignerdranch.android.roomshoppinglistkotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(ShoppingItems::class), version = 1, exportSchema = false)
        abstract class AppDatabase : RoomDatabase() {

    abstract fun shoppingItemsDao(): ShoppingItemsDao

    companion object {
        private lateinit var sAppDatabase: AppDatabase

        fun getDatabase(context: Context): AppDatabase {

            if (sAppDatabase == null) {
                sAppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "shopping-list").build()
            }
            return sAppDatabase
        }
    }


}
