package com.youyan.android.headlines.ui.view

import com.youyan.android.headlines.ui.adapter.RecommendItemAdapter
import com.youyan.android.headlines.ui.base.BaseView
import com.youyan.android.headlines.ui.model.NewsData

interface NewsView : BaseView {

    fun onGetNewsResponseResult(newsDataList: ArrayList<NewsData>)
}