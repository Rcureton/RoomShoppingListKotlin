package com.bignerdranch.android.roomshoppinglistkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.bignerdranch.android.roomshoppinglistkotlin.database.AppDatabase
import com.bignerdranch.android.roomshoppinglistkotlin.database.ShoppingItems
import io.reactivex.Flowable
import io.reactivex.Observable


class ShoppingListViewModel : AndroidViewModel {

    constructor(application: Application?): super(application){
        mDatabase = AppDatabase.getDatabase(this.getApplication())

    }

    private var mDatabase: AppDatabase? = null

    fun addItem(shoppingItems: ShoppingItems): Unit? {
        return mDatabase?.shoppingItemsDao()?.insertItems(shoppingItems)
    }

    fun getItems(): Flowable<List<ShoppingItems>> {
        return mDatabase?.shoppingItemsDao()!!.getAllItems()
    }

    fun deleteItem(shoppingItems: ShoppingItems): Observable<Unit?>? {
        return Observable.fromCallable { mDatabase?.shoppingItemsDao()?.deleteItem(shoppingItems) }
    }

}
