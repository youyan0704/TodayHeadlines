package com.youyan.android.kotlinapp.model

/**
 * Created by android on 3/26/18.
 */
data class Recommend(
        val id : Int,
        val title: String,
        val image: String,
        val category: String,
        val source: String,
        val comment: String,
        val publishTime: String,
//        val delete: String,
        var read: Boolean
)