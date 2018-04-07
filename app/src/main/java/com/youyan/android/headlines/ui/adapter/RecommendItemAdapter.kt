package com.youyan.android.headlines.ui.adapter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.qmuiteam.qmui.widget.QMUIAnimationListView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.CommonViewHolder
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.ui.model.Recommend
import com.youyan.android.headlines.utils.DateUtil
import java.util.*

/**
 * Created by android on 3/26/18.
 */
class RecommendItemAdapter(val context: Context?,
                           val recommends: ArrayList<NewsData>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder = CommonViewHolder.getViewHolder(context, R.layout.fragment_recommend_item, position, convertView, parent)
        viewHolder.setText(R.id.title,recommends[position].title?:"")
        viewHolder.setTextColor(R.id.title,if (recommends[position].read) Color.GRAY else Color.BLACK)
        viewHolder.setImageFromUrl(R.id.image, Uri.parse(recommends[position].middle_image.url?:""))
        viewHolder.setText(R.id.category,recommends[position].source?:"")
        viewHolder.isVisiable(R.id.category,if (recommends[position].source.isEmpty()) View.GONE else View.VISIBLE)
        viewHolder.setText(R.id.source,recommends[position].source?:"")
        viewHolder.setText(R.id.comment, recommends[position].comment_count.toString() + "评论" ?:"")
        viewHolder.setText(R.id.publishTime, DateUtil.convertSecond2Day(recommends[position].publish_time,null))
        viewHolder.isVisiable(R.id.delete,View.VISIBLE)

        viewHolder.getView<ImageView>(R.id.delete)!!.setOnClickListener(View.OnClickListener {
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

        viewHolder.convertView!!.setOnClickListener(View.OnClickListener {
            recommends[position].read = true
            notifyDataSetChanged()
//            ToastUtil.showShort(context,recommends[position].title?:"")
        })

        return viewHolder.convertView!!
    }

    override fun getItem(position: Int): Any {
       return recommends[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = recommends.size

    fun update(newData: ArrayList<NewsData>){
        recommends.addAll(newData)
        notifyDataSetChanged()
    }

}