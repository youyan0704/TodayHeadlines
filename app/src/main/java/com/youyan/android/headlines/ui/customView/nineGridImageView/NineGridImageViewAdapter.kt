package com.youyan.android.headlines.ui.customView.nineGridImageView

import android.content.Context
import android.widget.ImageView
import com.youyan.android.headlines.R

abstract class NineGridImageViewAdapter<T> {
    abstract fun onDisplayImage(context: Context, imageView: ImageView, t: T)

    abstract fun onItemImageClick(context: Context, index: Int, list: List<T>)

    fun generateImageView(context: Context): ImageView {
        val imageView = GridImageView(context)
        imageView.background = context.resources.getDrawable(R.color.alpha_grey,null)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return imageView
    }
}