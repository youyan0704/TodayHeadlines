package com.youyan.android.headlines.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.youyan.android.headlines.R
import com.youyan.android.headlines.app.AppManager

open class BaseActivity<P:BasePresenter<*>> : RxAppCompatActivity(),BaseView {
    override fun showLoading(){
    }

    override fun hideLoading() {
    }

    override fun onGetResultError() {
    }

    lateinit var presenter: P
    lateinit var loadingView: View
    lateinit var errorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        loadingView = layoutInflater.inflate(R.layout.layout_loading,null)
        errorView = layoutInflater.inflate(R.layout.layout_error,null)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}