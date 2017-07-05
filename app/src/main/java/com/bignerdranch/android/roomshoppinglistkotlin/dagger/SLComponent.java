package com.bignerdranch.android.roomshoppinglistkotlin.dagger;

import com.bignerdranch.android.roomshoppinglistkotlin.ShoppingListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SLModule.class})
public interface SLComponent {

    void inject(ShoppingListViewModel viewModel);


}
