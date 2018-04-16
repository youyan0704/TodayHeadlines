package com.youyan.android.headlines.common

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.youyan.android.headlines.R
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by android on 3/27/18.
 */
fun getHtml(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url(url)
            .build()

    val response = client.newCall(request).execute()

    return response.body()?.string() ?: ""
}

fun ImageView.loadUrl(url: String){
    Picasso.get()
            .load(Uri.parse(url))
            .placeholder(R.mipmap.app_logo)
            .into(this)
}