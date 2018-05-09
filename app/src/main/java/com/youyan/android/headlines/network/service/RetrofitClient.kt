package com.youyan.android.headlines.network.service

import com.youyan.android.headlines.network.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit

class RetrofitClient {

    constructor(url: String = baseUrl){

        //okHttpClient
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor {
                    chain -> val request = chain.request()
                        .newBuilder()
                        .addHeader("Content_Type","application/json")
                        .addHeader("charset","UTF-8")
//                        .addHeader("token",AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                        .build()

                    chain.proceed(request)
                }
                .proxy(Proxy.NO_PROXY)  //防止被抓包
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

        //retrofit
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    fun create(): ApiService{

        return retrofit.create(ApiService::class.java)
    }

    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }

    companion object {

        private const val baseUrl: String = Constant.BASE_SERVER_URL
        private const val DEFAULT_TIMEOUT: Int = 10
        lateinit var okHttpClient: OkHttpClient
        lateinit var retrofit: Retrofit

        fun getInstance(): RetrofitClient {
            return RetrofitClient()
        }

        fun getInstance(url: String): RetrofitClient {
            return RetrofitClient(url)
        }
    }
}