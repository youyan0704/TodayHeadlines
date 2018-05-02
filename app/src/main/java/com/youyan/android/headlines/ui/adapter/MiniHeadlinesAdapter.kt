package com.youyan.android.headlines.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youyan.android.headlines.R
import com.youyan.android.headlines.ui.model.MiniHeadlines
import com.youyan.android.headlines.utils.LoggerUtil

class MiniHeadlinesAdapter(ResId : Int,dataList : ArrayList<MiniHeadlines>)
    : BaseQuickAdapter<MiniHeadlines, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, item: MiniHeadlines) {
//        helper.getView<ImageView>(R.id.header).loadUrl(item.user_info.avatar_url)
//        helper.setText(R.id.username,item.user_info.name)
        LoggerUtil.i("MiniHeadlines",item.toString())
//        helper.setText(R.id.content,item.title)
    }



}