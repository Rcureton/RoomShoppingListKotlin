package com.bignerdranch.android.roomshoppinglistkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.bignerdranch.android.roomshoppinglistkotlin.database.AppDatabase
import com.bignerdranch.android.roomshoppinglistkotlin.database.ShoppingItems
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ShoppingListViewModel(application: Application?): AndroidViewModel(application) {

    private var mDatabase: AppDatabase? = null
    private var mShoppingItems: Flowable<List<ShoppingItems>> = null!!
    val compositeDisposable = CompositeDisposable()

    init {
        mDatabase = AppDatabase.getDatabase(this.getApplication())
        mShoppingItems = mDatabase?.shoppingItemsDao()!!.getAllItems()
    }

    fun addItem(shoppingItems: ShoppingItems) {
        compositeDisposable.add(Observable.fromCallable {mDatabase?.shoppingItemsDao()?.insertItems(shoppingItems)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun getItems(): Flowable<List<ShoppingItems>> {
        return mShoppingItems
    }

    fun deleteItem(shoppingItems: ShoppingItems) {
        compositeDisposable.delete(Observable.fromCallable { mDatabase?.shoppingItemsDao()?.deleteItem(shoppingItems)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

}
