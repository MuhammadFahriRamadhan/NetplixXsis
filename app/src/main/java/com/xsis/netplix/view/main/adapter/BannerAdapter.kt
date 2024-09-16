package com.xsis.netplix.view.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.AbstractAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.util.loadImage
import com.xsis.netplix.databinding.ItemBannerBinding


class BannerAdapter(val resultMovie: ResultMovie?)  : AbstractBindingItem<ItemBannerBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemBannerBinding {
        return ItemBannerBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemBannerBinding, payloads: List<Any>) {
        binding.run {
            Log.i("TAGED", "bindView: "+resultMovie?.posterPath)
            itemBannerImg.loadImage(resultMovie?.posterPath.orEmpty())
            tvTitleBanner.text = resultMovie?.title
            tvDescriptionBanner.text = resultMovie?.overview
        }
    }

}