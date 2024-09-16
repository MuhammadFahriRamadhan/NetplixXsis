package com.xsis.netplix.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.xsis.netplix.databinding.ItemTitleBinding

class TitleAdapter(val titleGenre : String) : AbstractBindingItem<ItemTitleBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemTitleBinding {
        return ItemTitleBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemTitleBinding, payloads: List<Any>) {
        binding.run {
            tvTitle.text = titleGenre
        }
    }
}