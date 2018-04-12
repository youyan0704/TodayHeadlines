package com.youyan.android.headlines.ui.base

import android.app.Fragment
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxFragment
import com.youyan.android.headlines.app.BaseApplicatoin
import com.youyan.android.headlines.injection.component.ActivityComponent
import com.youyan.android.headlines.injection.component.DaggerAppComponent
import com.youyan.android.headlines.injection.component.DaggerFragmentComponent
import com.youyan.android.headlines.injection.component.FragmentComponent
import com.youyan.android.headlines.injection.module.ContextModule
import com.youyan.android.headlines.injection.module.FragmentModule
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

open class BaseFragment<P:BasePresenter<*>> : RxFragment(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(msg:String) {
        toast(msg)
    }

    @Inject
    lateinit var mBasePresenter: P

    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentComponenttInjection()


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

    }

    private fun initFragmentComponenttInjection() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(((activity as BaseActivity<*>).application as BaseApplicatoin).appComponent)
                .fragmentModule(FragmentModule(this))
                .build()
    }
}