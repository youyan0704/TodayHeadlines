package com.youyan.android.headlines.ui.view

import com.youyan.android.headlines.ui.base.BaseView
import com.youyan.android.headlines.ui.model.NewsResponse

interface NewsView : BaseView {

    fun onGetNewsResponseResult(newsResponse: NewsResponse)
}