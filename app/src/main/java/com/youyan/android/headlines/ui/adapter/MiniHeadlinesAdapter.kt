package com.youyan.android.headlines.ui.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.loadUrl
import com.youyan.android.headlines.ui.model.MiniHeadlines
import com.youyan.android.headlines.utils.DateUtil
import com.youyan.android.headlines.utils.LoggerUtil

class MiniHeadlinesAdapter(ResId : Int,dataList : ArrayList<MiniHeadlines>)
    : BaseQuickAdapter<MiniHeadlines, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, miniHeadlines: MiniHeadlines) {
        LoggerUtil.i("share_url",miniHeadlines.share_url)

        if (miniHeadlines.user != null){
            helper.getView<ImageView>(R.id.header).loadUrl(miniHeadlines.user.avatar_url)
            helper.setText(R.id.username,miniHeadlines.user.name)
            helper.setText(R.id.publishTime,DateUtil.convertSecond2Day(miniHeadlines.create_time.toLong(),null))
            helper.setText(R.id.userCatergray,miniHeadlines.user.verified_content)
            helper.getView<ImageView>(R.id.dislike).visibility = if (miniHeadlines.show_dislike) View.VISIBLE else View.GONE
            helper.setText(R.id.content,miniHeadlines.content)
//            helper.setText(R.id.share,miniHeadlines.share_count.toString())
            helper.setText(R.id.comment_count,miniHeadlines.comment_count.toString())
//            helper.setText(R.id.like,miniHeadlines.digg_count.toString())

        }
    }



}
