package com.youyan.android.headlines.ui.view

import com.youyan.android.headlines.ui.base.BaseView
import com.youyan.android.headlines.ui.model.Headlines

interface HeadlinesView : BaseView {

    fun onGetHeadlinesResponseResult(headlinesList: ArrayList<Headlines>)
}