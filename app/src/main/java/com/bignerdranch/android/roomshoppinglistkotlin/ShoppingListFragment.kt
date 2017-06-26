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

class ShoppingListFragment : LifecycleFragment() {

    private lateinit var binding: FragmentShoppingListBinding
    private var mShoppingItems: List<ShoppingItems> = null!!
    private var mViewModel: ShoppingListViewModel

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



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_shopping, menu)
    }

    //TODO: FINISH THIS METHOD
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val shoppingItem = ShoppingItems()
        when (item?.itemId) {
            R.id.add_shopping_item ->
        }
        return super.onOptionsItemSelected(item)
    }


    class ShoppingListItemHolder(binding: ListShoppingItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var listBinding: ListShoppingItemBinding = null!!
        private var shoppingItem: ShoppingItems

        override fun onClick(v: View?) {
            if (v == listBinding.listItemDeleteButton) {
                ShoppingListViewModel.deleteItem(shoppingItem)
            } else {
                //TODO: WRITE INTENT
            }
        }

        fun bind(shoppingItems: ShoppingItems) {
            itemView.setOnClickListener(this)
            listBinding.listItemTitleTextView.setText(shoppingItems.item)
            listBinding.listItemStoreTextView.setText(shoppingItems.store)
            listBinding.listItemDateTextView.setText(shoppingItems.date.toString())
        }
    }

    class ShoppingListAdapter(shoppingItems: List<ShoppingItems>) : RecyclerView.Adapter<ShoppingListItemHolder>() {
        private var items: List<ShoppingItems> = null!!
        private var binding: ListShoppingItemBinding

        override fun onBindViewHolder(parent: ShoppingListItemHolder?, i: Int) {
            var shoppingItem = ShoppingItems()
            shoppingItem = items.get(i)
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
    }

}

