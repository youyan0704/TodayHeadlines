package com.youyan.android.headlines.ui.view

import com.youyan.android.headlines.ui.base.BaseView
import com.youyan.android.headlines.ui.model.HeadlinesResponse

interface MiniHeadlinesView : BaseView {

    fun onGetMiniHeadlinesResponseResult(headlinesResponse: HeadlinesResponse)
}