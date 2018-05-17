package com.youyan.android.headlines.ui.adapter

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
import android.support.v7.widget.LinearLayoutManager
import com.youyan.android.headlines.ui.ItemDecoration.RecyclerViewDivider


class MiniHeadlinesAdapter(ResId : Int,dataList : ArrayList<MiniHeadlines>)
    : BaseQuickAdapter<MiniHeadlines, BaseViewHolder>(ResId,dataList) {

    override fun convert(helper: BaseViewHolder, miniHeadlines: MiniHeadlines) {
        LoggerUtil.i("ThumbImage",miniHeadlines.thumb_image_list.toString())

        helper.getView<ImageView>(R.id.header).loadUrl(miniHeadlines.user.avatar_url)
        helper.getView<ImageView>(R.id.dislike).visibility = if (miniHeadlines.show_dislike) View.VISIBLE else View.GONE
        if (miniHeadlines.large_image_list.isNotEmpty()){
            LoggerUtil.i("large_image_list",miniHeadlines.large_image_list.toString())
            /*helper.getView<ImageView>(R.id.large_image).visibility = View.VISIBLE
            helper.getView<ImageView>(R.id.large_image).loadUrl(miniHeadlines.large_image_list[0].url)*/
            val largeImageList = miniHeadlines.large_image_list
            val imageRecyclerView = helper.getView<RecyclerView>(R.id.imageRecyclerView)
            val imageAdapter = ImageAdapter(R.layout.layout_mini_headlines_image_item,largeImageList)
            var gridLayoutManager = GridLayoutManager(mContext, 3)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    when(largeImageList.size){
                        1 -> {
                            return 3
                        }
                        2,3,4 -> {
                            return 2
                        }
                        5,6,7,8,9 -> {
                            return 1
                        }
                    }
                    return 1
                }

            }

            imageRecyclerView.layoutManager = gridLayoutManager
            imageRecyclerView.adapter = imageAdapter

        }
        helper.setText(R.id.username,miniHeadlines.user.name)
                .setText(R.id.publishTime,DateUtil.convertSecond2Day(miniHeadlines.create_time.toLong(),null))
                .setText(R.id.userCatergray,miniHeadlines.user.verified_content)
                .setText(R.id.content,miniHeadlines.content)
//                    .setText(R.id.share,miniHeadlines.share_count.toString())
                .setText(R.id.comment_count,miniHeadlines.comment_count.toString())
//                    .setText(R.id.like,miniHeadlines.digg_count.toString())
                .addOnClickListener(R.id.dislike)
                .addOnClickListener(R.id.follow)
                .addOnClickListener(R.id.share)
                .addOnClickListener(R.id.comment_count)
                .addOnClickListener(R.id.like)


    }

    class ImageAdapter(ResId : Int,dataList : List<LargeImage>)
        : BaseQuickAdapter<LargeImage, BaseViewHolder>(ResId,dataList){
        override fun convert(helper: BaseViewHolder, item: LargeImage) {
            helper.getView<ImageView>(R.id.image_item).loadUrl(item.url)
        }

    }

}
