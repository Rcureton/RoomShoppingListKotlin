package com.bignerdranch.android.roomshoppinglistkotlin.database;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ItemsRepositoryImpl implements ItemsRepository {

    @Inject
    AppDatabase mAppDatabase;

    public ItemsRepositoryImpl(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    @Override
    public Completable addItem(final ShoppingItems shoppingItems) {
        return Completable.fromAction(() -> mAppDatabase.shoppingItemsDao().insertItems(shoppingItems));
    }

    @Override
    public Flowable<List<ShoppingItems>> getItems() {
        return mAppDatabase.shoppingItemsDao().getAllItems();
    }

    @Override
    public Completable deleteItem(ShoppingItems shoppingItems) {
        return Completable.fromAction(() -> mAppDatabase.shoppingItemsDao().deleteItem(shoppingItems));
    }
}
