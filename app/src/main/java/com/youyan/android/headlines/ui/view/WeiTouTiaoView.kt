package com.youyan.android.headlines.ui.view

import com.youyan.android.headlines.ui.base.BaseView
import com.youyan.android.headlines.ui.model.NewsData

interface WeiTouTiaoView : BaseView {

    fun onGetWeiResponseResult(newsDataList: ArrayList<NewsData>)
}