package com.youyan.android.headlines.ui.base

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.youyan.android.headlines.app.AppManager
import com.youyan.android.headlines.app.BaseApplicatoin
import com.youyan.android.headlines.injection.component.ActivityComponent
import com.youyan.android.headlines.injection.component.DaggerActivityComponent
import com.youyan.android.headlines.injection.module.ActivityModule
import org.jetbrains.anko.toast

open class BaseActivity<P:BasePresenter<*>> : RxAppCompatActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(msg:String) {
        toast(msg)
    }

    lateinit var mBasePresenter: P
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)

        initAppComponentInjection()
    }

    private fun initAppComponentInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplicatoin).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}