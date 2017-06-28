package com.bignerdranch.android.roomshoppinglistkotlin

import android.arch.lifecycle.LifecycleFragment
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.bignerdranch.android.roomshoppinglistkotlin.database.ShoppingItems
import com.bignerdranch.android.roomshoppinglistkotlin.databinding.FragmentShoppingListBinding
import com.bignerdranch.android.roomshoppinglistkotlin.databinding.ListShoppingItemBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShoppingListFragment : LifecycleFragment() {


    private lateinit var binding: FragmentShoppingListBinding
    private var mShoppingItems = mutableListOf<ShoppingItems>()
    private lateinit var mViewModel: ShoppingListViewModel
    private lateinit var mAdapter: ShoppingListAdapter

    companion object {
        fun newInstance(): ShoppingListFragment {
            return ShoppingListFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_list, container, false)
        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        mViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
        val adapter = ShoppingListAdapter(mShoppingItems)
        ShoppingListApplication.mAppDatabase?.shoppingItemsDao()?.getAllItems()?.
                observeOn(Schedulers.io())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    list -> adapter.setItems(list)
                }
//        mViewModel.getItems().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe { listOfItems ->
//            adapter.setItems(listOfItems)
//        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_shopping, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add_shopping_item -> {
                val intent = Intent(context, ShoppingItemActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    inner class ShoppingListItemHolder(binding: ListShoppingItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var listBinding: ListShoppingItemBinding
        private var shoppingItem: ShoppingItems = null!!

        override fun onClick(v: View?) {
            if (v == listBinding.listItemDeleteButton) {
//                mViewModel.deleteItem(shoppingItem)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())

            } else {
                val intent = Intent(context, ShoppingItemActivity::class.java)
                startActivity(intent)
            }
        }

        fun bind(shoppingItems: ShoppingItems) {
            itemView.setOnClickListener(this)
            listBinding.listItemTitleTextView.text = shoppingItems.item
            listBinding.listItemStoreTextView.text = shoppingItems.store
            listBinding.listItemDateTextView.text = shoppingItems.date.toString()
        }
    }

    inner class ShoppingListAdapter(shoppingItems: MutableList<ShoppingItems>) : RecyclerView.Adapter<ShoppingListItemHolder>() {
        private lateinit var binding: ListShoppingItemBinding
        private var items = mutableListOf<ShoppingItems>()

        override fun onBindViewHolder(parent: ShoppingListItemHolder?, i: Int) {
            val shoppingItem = items[i]
            parent?.bind(shoppingItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): ShoppingListItemHolder {
            var context = parent?.context
            var inflater = LayoutInflater.from(context)
            binding = DataBindingUtil.inflate(inflater, R.layout.list_shopping_item, parent, false)
            return ShoppingListItemHolder(binding)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        fun setItems(shoppingItems: List<ShoppingItems>) {
            mShoppingItems.clear()
            mShoppingItems.addAll(shoppingItems)
            notifyDataSetChanged()
        }
    }

}

