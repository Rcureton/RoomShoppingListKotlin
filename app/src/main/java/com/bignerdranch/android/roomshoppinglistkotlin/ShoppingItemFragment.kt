package com.bignerdranch.android.roomshoppinglistkotlin

import android.arch.lifecycle.LifecycleFragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.roomshoppinglistkotlin.database.ShoppingItems
import com.bignerdranch.android.roomshoppinglistkotlin.databinding.FragmentShoppingItemBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ShoppingItemFragment : LifecycleFragment() {

    private lateinit var binding: FragmentShoppingItemBinding
    private lateinit var mDate: Date
    private lateinit var mItemTitle: String
    private lateinit var mStoreName: String

//    private var mViewModel: ShoppingListViewModel = null!!


    companion object {
        fun newInstance(): ShoppingItemFragment {
            return ShoppingItemFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        mViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_item, container, false)

        mDate = Date()

        binding.fragmentShoppingDateButton.setOnClickListener { v ->
            mItemTitle = binding.fragmentShoppingEditText.text.toString()
            mStoreName = binding.fragmentShoppingStoreEditText.text.toString()

            binding.fragmentShoppingDateButton.text = mDate.time.toString()
        }

        binding.fragmentShoppingSaveButton.setOnClickListener { v ->
            val shoppingItem = ShoppingItems(0, mDate, mStoreName, mItemTitle)

            Single.fromCallable {
                ShoppingListApplication.mAppDatabase?.shoppingItemsDao()?.insertItems(shoppingItem)}
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
            }

        return binding.root
    }
}




