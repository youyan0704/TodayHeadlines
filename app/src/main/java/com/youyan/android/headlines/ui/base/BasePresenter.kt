package com.youyan.android.headlines.ui.base

import com.trello.rxlifecycle2.LifecycleProvider
import com.youyan.android.headlines.network.service.ApiService
import com.youyan.android.headlines.network.service.RetrofitClient
import javax.inject.Inject

open class BasePresenter<T: BaseView> {

    val apiService: ApiService = RetrofitClient.getInstance().createBaseApi()

    lateinit var mBaseView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}