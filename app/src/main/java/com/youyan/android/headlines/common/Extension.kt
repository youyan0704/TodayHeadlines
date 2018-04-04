package com.youyan.android.headlines.common

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