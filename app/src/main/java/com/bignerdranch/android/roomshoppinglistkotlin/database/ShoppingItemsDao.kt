package com.bignerdranch.android.roomshoppinglistkotlin.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao interface ShoppingItemsDao {

    @Query("select * from shoppingItem")
    fun getAllItems(): Flowable<List<ShoppingItems>>

    @Update
    fun updateItem(shoppingItems: ShoppingItems)

    @Insert
    fun insertItems(shoppingItems: ShoppingItems)

    @Delete
    fun deleteItem(shoppingItems: ShoppingItems)
}