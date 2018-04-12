package com.youyan.android.headlines.ui.base

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.youyan.android.headlines.R
import com.youyan.android.headlines.network.service.ApiService
import com.youyan.android.headlines.network.service.RetrofitClient
import com.youyan.android.headlines.utils.NetworkUtil
import dagger.Provides
import javax.inject.Inject

open class BasePresenter<T: BaseView> {

    val apiService: ApiService

    init {
        apiService = RetrofitClient.getInstance().createBaseApi()
    }

    lateinit var mBaseView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun isNetworkAvaliable():Boolean{
        if (NetworkUtil.isConnected(context)){
            return true
        }
        mBaseView.onError(context.getString(R.string.noNetork))
        return false
    }
}