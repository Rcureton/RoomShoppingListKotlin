package com.bignerdranch.android.roomshoppinglistkotlin

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.roomshoppinglistkotlin.databinding.FragmentShoppingListBinding

class ShoppingListFragment : LifecycleFragment() {

    private lateinit var binding: FragmentShoppingListBinding

    companion object {
        fun newInstance() : ShoppingListFragment {
            return ShoppingListFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}

