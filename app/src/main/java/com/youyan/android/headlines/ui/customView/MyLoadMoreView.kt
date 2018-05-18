package com.youyan.android.headlines.ui.customView

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.youyan.android.headlines.R

open class MyLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int {
       return R.layout.view_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadEndViewId(): Int {
        return 0
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }
}