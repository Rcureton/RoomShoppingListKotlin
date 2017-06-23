package com.bignerdranch.android.roomshoppinglistkotlin.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.util.*

@Entity(tableName = "shoppingItems")
@TypeConverters(DateConverter::class)
data class ShoppingItems (
        @PrimaryKey(autoGenerate = true) val id: Int, @ColumnInfo(name = "date") var date: Date,
        @ColumnInfo(name = "item") var item: String, @ColumnInfo(name = "store") var store: String)


