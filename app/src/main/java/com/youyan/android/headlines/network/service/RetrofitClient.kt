package com.youyan.android.headlines.network.service

import android.content.Context
import com.youyan.android.headlines.network.Constant
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitClient {

    constructor(url: String = baseUrl, headers: Map<String, String>? = null){

//        //缓存地址
//        if (httpCacheDirectory == null) {
//            httpCacheDirectory = File(BaseApplicatoin().context().cacheDir, "cache")
//        }
//
//        try {
//            if (cache == null) {
//                cache = Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())
//            }
//        } catch (e: Exception) {
//            Log.e("OKHttp", "Could not create http cache", e)
//        }

        //创建okHttpClient
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .cache(cache)
//                .addInterceptor(BaseInterceptor(headers))
//                .addInterceptor(CacheInterceptor(context))
//                .addNetworkInterceptor(CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

        //创建retrofit
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    fun createBaseApi(): ApiService{

        return retrofit.create(ApiService::class.java)
    }

    companion object {

        private val baseUrl: String = Constant.BASE_SERVER_URL
        private val DEFAULT_TIMEOUT: Int = 20

        lateinit var mContext: Context
        private var httpCacheDirectory: File? = null
        private var cache: Cache? = null
        lateinit var okHttpClient: OkHttpClient
        lateinit var retrofit: Retrofit

        fun getInstance(): RetrofitClient {
            return RetrofitClient()
        }

        fun getInstance(url: String): RetrofitClient {
            return RetrofitClient(url)
        }

        fun getInstance(url: String, headers: Map<String, String>): RetrofitClient {
            return RetrofitClient(url, headers)
        }
    }
}