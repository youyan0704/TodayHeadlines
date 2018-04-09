package com.youyan.android.headlines.ui.base

import android.support.v4.app.Fragment
import javax.inject.Inject

open class BaseFragment<P:BasePresenter<*>> : Fragment(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mBasePresenter: P
}