package com.youyan.android.headlines.ui.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.loadUrl
import com.youyan.android.headlines.ui.model.Headlines
import com.youyan.android.headlines.utils.DateUtil
import com.youyan.android.headlines.utils.LoggerUtil

class HeadlinesAdapter2(ResId : Int, dataList : ArrayList<Headlines>)
    : BaseQuickAdapter<Headlines, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, headlines: Headlines) {
        helper.setText(R.id.title,headlines.title?:"")
        helper.setTextColor(R.id.title,if (headlines.read) Color.GRAY else Color.BLACK)
        if (headlines.has_image){
            helper.getView<ImageView>(R.id.image)!!.visibility = View.VISIBLE
            helper.getView<ImageView>(R.id.image)!!.loadUrl(headlines.middle_image.url)
        }else{
            helper.getView<ImageView>(R.id.image)!!.visibility = View.GONE
        }
        helper.setVisible(R.id.category, headlines.hot != 0)
        helper.setText(R.id.category,if (headlines.hot == 0) "" else "热")

        helper.setText(R.id.source,headlines.source?:"")
        helper.setText(R.id.comment, headlines.comment_count.toString() + "评论")
        helper.setText(R.id.publishTime, DateUtil.convertSecond2Day(headlines.publish_time.toLong(),null))
        helper.setVisible(R.id.delete,true)
    }
}
