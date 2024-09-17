package com.xsis.netplix.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.xsis.netplix.databinding.ItemEmptyMovieBinding

class EmptyAdapter(val title : String?) : AbstractBindingItem<ItemEmptyMovieBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmptyMovieBinding {
      return ItemEmptyMovieBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemEmptyMovieBinding, payloads: List<Any>) {
        binding.run {
            titleEmpty.text = title.toString()
        }
    }
}