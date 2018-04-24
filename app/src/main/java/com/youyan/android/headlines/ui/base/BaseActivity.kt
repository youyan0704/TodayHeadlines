package com.youyan.android.headlines.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.youyan.android.headlines.app.AppManager

open class BaseActivity<P:BasePresenter<*>> : RxAppCompatActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}