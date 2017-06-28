package com.bignerdranch.android.roomshoppinglistkotlin

import android.app.Application
import android.arch.persistence.room.Room

import com.bignerdranch.android.roomshoppinglistkotlin.database.AppDatabase

class ShoppingListApplication : Application() {

    companion object {
        var mAppDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        mAppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "shopping-list").build()
    }
}
