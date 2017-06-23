package com.bignerdranch.android.roomshoppinglistkotlin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao interface ShoppingItemsDao {

    @Query("select * from shoppingItem")
    fun getAllItems(): LiveData<List<ShoppingItems>>

    @Update
    fun updateItem(shoppingItems: ShoppingItems)

    @Insert
    fun insertItems(shoppingItems: ShoppingItems)

    @Delete
    fun deleteItem(shoppingItems: ShoppingItems)
}