package com.youyan.android.headlines.ui.base

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity<P:BasePresenter<*>> : RxAppCompatActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var presenter: P

}