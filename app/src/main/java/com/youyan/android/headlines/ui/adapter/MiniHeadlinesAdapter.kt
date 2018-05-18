package com.youyan.android.headlines.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.loadUrl
import com.youyan.android.headlines.ui.model.LargeImage
import com.youyan.android.headlines.ui.model.MiniHeadlines
import com.youyan.android.headlines.utils.DateUtil
import com.youyan.android.headlines.utils.LoggerUtil
import android.support.v7.widget.GridLayoutManager
import com.jaeger.ninegridimageview.NineGridImageView
import com.jaeger.ninegridimageview.NineGridImageViewAdapter


class MiniHeadlinesAdapter(ResId : Int,dataList : ArrayList<MiniHeadlines>)
    : BaseQuickAdapter<MiniHeadlines, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, miniHeadlines: MiniHeadlines) {

        helper.getView<ImageView>(R.id.header).loadUrl(miniHeadlines.user.avatar_url)
        helper.getView<ImageView>(R.id.dislike).visibility = if (miniHeadlines.show_dislike) View.VISIBLE else View.GONE
        if (miniHeadlines.large_image_list.isNotEmpty()){
            LoggerUtil.i("large_image_list",miniHeadlines.large_image_list.toString())

            val largeImageList = miniHeadlines.large_image_list
            val nineGridImageView = helper.getView<NineGridImageView<*>>(R.id.nineGridImageView)

            val nineImageAdapter = object : NineGridImageViewAdapter<LargeImage>() {
                override fun onDisplayImage(context: Context, imageView: ImageView, t: LargeImage) {
                    imageView.loadUrl(t.url,t.width,t.height)
                }

                override fun onItemImageClick(context: Context, index: Int, list: MutableList<LargeImage>) {

                }

            }
            nineGridImageView.setAdapter(nineImageAdapter)
            nineGridImageView.setImagesData(largeImageList)

        }
        helper.setText(R.id.username,miniHeadlines.user.name)
                .setText(R.id.publishTime,DateUtil.convertSecond2Day(miniHeadlines.create_time.toLong(),null))
                .setText(R.id.userCatergray,miniHeadlines.user.verified_content)
                .setText(R.id.content,miniHeadlines.content)
                .setText(R.id.forward_count,miniHeadlines.forward_info.forward_count.toString())
                .setText(R.id.comment_count,miniHeadlines.comment_count.toString())
                .setText(R.id.like_count,miniHeadlines.digg_count.toString())
                .addOnClickListener(R.id.dislike)
                .addOnClickListener(R.id.follow)
                .addOnClickListener(R.id.share)
                .addOnClickListener(R.id.comment_count)
                .addOnClickListener(R.id.like)

    }

}
