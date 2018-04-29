package com.youyan.android.headlines.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.loadUrl
import com.youyan.android.headlines.ui.model.NewsData

class MiniHeadlinesAdapter(ResId : Int,dataList : ArrayList<NewsData>)
    : BaseQuickAdapter<NewsData, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, item: NewsData) {
        helper.getView<ImageView>(R.id.header).loadUrl(item.user_info.avatar_url)
        helper.setText(R.id.username,item.user_info.name)
    }
}