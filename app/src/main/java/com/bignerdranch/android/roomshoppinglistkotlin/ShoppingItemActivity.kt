package com.bignerdranch.android.roomshoppinglistkotlin

import android.support.v4.app.Fragment

class ShoppingItemActivity : SLSingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return ShoppingItemFragment.newInstance()
    }

}
