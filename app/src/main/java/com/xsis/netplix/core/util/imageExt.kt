package com.xsis.netplix.core.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.xsis.netplix.R

fun ImageView.loadImage(imageUrl : String, @DrawableRes placeholder: Int = R.drawable.ic_launcher_background) {
    Glide.with(this)
        .load("https://image.tmdb.org/t/p/w185"+imageUrl)
        .placeholder(placeholder)
        .into(this)

}