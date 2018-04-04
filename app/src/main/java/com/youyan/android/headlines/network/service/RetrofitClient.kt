package com.youyan.android.headlines.network.service

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitClient {

    constructor(context: Context, url: String = baseUrl, headers: Map<String, String>? = null){

        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(context!!.cacheDir, "cache")
        }

        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }

        //创建okHttpClient
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
//                .addInterceptor(BaseInterceptor(headers))
//                .addInterceptor(CacheInterceptor(context))
//                .addNetworkInterceptor(CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

        //创建retrofit
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    fun createBaseApi(): RetrofitClient{

        retrofit.create(ApiService::class.java)
        return this
    }

    companion object {

        private val baseUrl: String = Constant.BASE_SERVER_URL
        private val DEFAULT_TIMEOUT: Int = 20

        lateinit var mContext: Context
        private lateinit var httpCacheDirectory: File
        private lateinit var cache: Cache
        lateinit var okHttpClient: OkHttpClient
        lateinit var retrofit: Retrofit

        fun getInstance(context: Context): RetrofitClient {
            if (context != null) {
                mContext = context
            }
            return RetrofitClient(context)
        }

        fun getInstance(context: Context, url: String): RetrofitClient {
            if (context != null) {
                mContext = context
            }

            return RetrofitClient(context, url)
        }

        fun getInstance(context: Context, url: String, headers: Map<String, String>): RetrofitClient {
            if (context != null) {
                mContext = context
            }
            return RetrofitClient(context, url, headers)
        }

    }
}