package com.youyan.android.headlines.ui.base

import android.support.v4.app.Fragment

open class BaseFragment<P:BasePresenter<*>> : Fragment(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var mBasePresenter: P
}