package com.bignerdranch.android.roomshoppinglistkotlin

import android.support.v4.app.Fragment

class ShoppingListActivity : SLSingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return ShoppingListFragment.newInstance()
    }

}
