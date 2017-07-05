package com.bignerdranch.android.roomshoppinglistkotlin.database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ItemsRepository {

    Completable addItem(ShoppingItems shoppingItems);

    Flowable<List<ShoppingItems>> getItems();

    Completable deleteItem(ShoppingItems shoppingItems);
}
