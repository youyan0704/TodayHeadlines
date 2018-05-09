package com.youyan.android.headlines.ui.base

import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle2.components.support.RxFragment
import com.youyan.android.headlines.R
import javax.inject.Inject

open class BaseFragment<P:BasePresenter<*>> : RxFragment(),BaseView {
    @Inject
    lateinit var mBasePresenter: P

    lateinit var loadingView: View

    lateinit var errorView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = layoutInflater.inflate(R.layout.layout_loading,null)
        errorView = layoutInflater.inflate(R.layout.layout_error,null)

    }

    override fun showLoading(){
    }

    override fun hideLoading() {
    }

    override fun onGetResultError() {
    }

}