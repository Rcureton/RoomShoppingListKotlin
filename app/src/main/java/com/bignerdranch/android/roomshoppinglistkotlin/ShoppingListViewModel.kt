package com.bignerdranch.android.roomshoppinglistkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.bignerdranch.android.roomshoppinglistkotlin.database.AppDatabase
import com.bignerdranch.android.roomshoppinglistkotlin.database.ShoppingItems

private var mDatabase: AppDatabase? = null
private var mShoppingItems: LiveData<List<ShoppingItems>> = null!!

class ShoppingListViewModel(application: Application?): AndroidViewModel(application) {

    init {
        mDatabase = AppDatabase.getDatabase(this.getApplication())
        mShoppingItems = mDatabase!!.shoppingItemsDao().getAllItems()
    }

    companion object {
        fun getItems(): LiveData<List<ShoppingItems>> {
            return mShoppingItems
        }
    }


}
