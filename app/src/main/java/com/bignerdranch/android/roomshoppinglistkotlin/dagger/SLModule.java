package com.bignerdranch.android.roomshoppinglistkotlin.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.bignerdranch.android.roomshoppinglistkotlin.ShoppingListApplication;
import com.bignerdranch.android.roomshoppinglistkotlin.database.AppDatabase;
import com.bignerdranch.android.roomshoppinglistkotlin.database.ItemsRepository;
import com.bignerdranch.android.roomshoppinglistkotlin.database.ItemsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SLModule {

    private ShoppingListApplication mShoppingListApplication;

    public SLModule(ShoppingListApplication shoppingListApplication) {
        mShoppingListApplication = shoppingListApplication;
    }

    @Provides Context applicationContext() {
        return mShoppingListApplication;
    }

    @Provides
    @Singleton
    ItemsRepository providesItemRepository(AppDatabase appDatabase) {
        return new ItemsRepositoryImpl(appDatabase);
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "shopping-list").build();
    }
}
