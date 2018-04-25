package com.youyan.android.headlines.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.qmuiteam.qmui.widget.QMUIAnimationListView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.CommonViewHolder
import com.youyan.android.headlines.ui.activity.WebviewActivity
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.utils.DateUtil
import java.util.*

/**
 * Created by android on 3/26/18.
 */
class RecommendItemAdapter(val context: Context,
                           private val recommends: ArrayList<NewsData>) : BaseAdapter() {

    lateinit var listener: OnItemClickListener

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder = CommonViewHolder.getViewHolder(context, R.layout.fragment_recommend_item, position, convertView, parent)
        val newsData = recommends[position]
        viewHolder.setText(R.id.title, newsData.title)
        viewHolder.setTextColor(R.id.title,if (newsData.read) Color.GRAY else Color.BLACK)
        viewHolder.setImageFromUrl(R.id.image, newsData.middle_image.url)
        viewHolder.isVisiable(R.id.category,if (newsData.hot.equals(0)) View.GONE else View.VISIBLE)
        viewHolder.setText(R.id.category,if (newsData.hot.equals(0)) "热" else "")

        viewHolder.setText(R.id.source, newsData.source)
        viewHolder.setText(R.id.comment, newsData.comment_count.toString() + "评论")
        viewHolder.setText(R.id.publishTime, DateUtil.convertSecond2Day(newsData.publish_time.toLong(),null))
        viewHolder.isVisiable(R.id.delete,View.VISIBLE)
/*        viewHolder.convertView?.setOnClickListener {
            listener.onItemClick(position)
        }*/

        viewHolder.getView<ImageView>(R.id.delete)!!.setOnClickListener({
            var tipDialog: QMUITipDialog
            parent as QMUIAnimationListView
            parent.manipulate(QMUIAnimationListView.Manipulator<RecommendItemAdapter> {
                recommends.removeAt(position)
                notifyDataSetChanged()
                tipDialog = QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord("删除成功")
                        .create()
                tipDialog.show()

                parent.postDelayed(Runnable {
                    tipDialog.dismiss()
                },1000)
            })
        })

        viewHolder.convertView!!.setOnClickListener({
            newsData.read = true
            notifyDataSetChanged()
            val intent = Intent(context,WebviewActivity::class.java)
            intent.putExtra("url",newsData.article_url)
            context.startActivity(intent)
        })

        return viewHolder.convertView!!
    }

    override fun getItem(position: Int): Any {
       return recommends[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = recommends.size

    fun update(newData: ArrayList<NewsData>){
        recommends.addAll(0,newData)
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}