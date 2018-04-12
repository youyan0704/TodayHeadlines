package com.youyan.android.headlines.ui.base

import com.trello.rxlifecycle2.components.support.RxFragment
import javax.inject.Inject

open class BaseFragment<P:BasePresenter<*>> : RxFragment(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mBasePresenter: P
}