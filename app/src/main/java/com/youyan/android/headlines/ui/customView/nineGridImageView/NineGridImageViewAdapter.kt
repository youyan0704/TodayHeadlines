package com.youyan.android.headlines.ui.customView.nineGridImageView

import android.content.Context
import android.widget.ImageView

abstract class NineGridImageViewAdapter<T> {
    abstract fun onDisplayImage(context: Context, imageView: ImageView, t: T)

    abstract fun onItemImageClick(context: Context, index: Int, list: List<T>)

    fun generateImageView(context: Context): ImageView {
        val imageView = GridImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return imageView
    }
}