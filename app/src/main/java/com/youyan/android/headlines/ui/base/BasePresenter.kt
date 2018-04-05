package com.youyan.android.headlines.ui.base

import com.youyan.android.headlines.network.service.ApiService
import com.youyan.android.headlines.network.service.RetrofitClient

open class BasePresenter<T: BaseView> {

    val apiService: ApiService

    init {
        apiService = RetrofitClient.getInstance().createBaseApi()
    }

    lateinit var mBaseView: T
}