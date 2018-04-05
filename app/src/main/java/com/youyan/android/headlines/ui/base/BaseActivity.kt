package com.youyan.android.headlines.ui.base

import android.support.v7.app.AppCompatActivity

open class BaseActivity<P:BasePresenter<*>> : AppCompatActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var presenter: P

}