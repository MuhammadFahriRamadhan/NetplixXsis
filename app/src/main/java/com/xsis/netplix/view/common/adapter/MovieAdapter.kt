package com.xsis.netplix.view.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.util.loadImage
import com.xsis.netplix.databinding.ItemMovieBinding

class MovieAdapter(val resultMovie: ResultMovie?)  : AbstractBindingItem<ItemMovieBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemMovieBinding {
       return ItemMovieBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemMovieBinding, payloads: List<Any>) {
        binding.run {
            tvTitle.text = resultMovie?.title
            imagePoster.loadImage(resultMovie?.posterPath.orEmpty())
        }
    }
}