package com.youyan.android.headlines.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.qmuiteam.qmui.widget.QMUIAnimationListView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.youyan.android.headlines.R
import com.youyan.android.headlines.common.CommonViewHolder
import com.youyan.android.headlines.ui.activity.WebViewActivity
import com.youyan.android.headlines.ui.model.Headlines
import com.youyan.android.headlines.utils.DateUtil
import java.util.*

/**
 * Created by android on 3/26/18.
 */
class HeadlinesAdapter(val context: Context?,
                       private val headlinesList: ArrayList<Headlines>) : BaseAdapter() {

    lateinit var listener: OnItemClickListener

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder = CommonViewHolder.getViewHolder(context, R.layout.fragment_recommend_item, position, convertView, parent)
        val headlines = headlinesList[position]
        viewHolder.setText(R.id.title,headlines.title)
        viewHolder.setTextColor(R.id.title,if (headlines.read) Color.GRAY else Color.BLACK)
        viewHolder.setImageFromUrl(R.id.image, headlines.middle_image.url)
        viewHolder.isVisiable(R.id.category,if (headlines.hot == 0) View.GONE else View.VISIBLE)
        viewHolder.setText(R.id.category,if (headlines.hot == 0) "" else "热")

        viewHolder.setText(R.id.source,headlines.source)
        viewHolder.setText(R.id.comment, headlines.comment_count.toString() + "评论")
        viewHolder.setText(R.id.publishTime, DateUtil.convertSecond2Day(headlines.publish_time.toLong(),null))
        viewHolder.isVisiable(R.id.delete,View.VISIBLE)
/*        viewHolder.convertView?.setOnClickListener {
            listener.onItemClick(position)
        }*/

        viewHolder.getView<ImageView>(R.id.delete)!!.setOnClickListener({
            var tipDialog: QMUITipDialog
            parent as QMUIAnimationListView
            parent.manipulate(QMUIAnimationListView.Manipulator<HeadlinesAdapter> {
                headlinesList.removeAt(position)
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

            notifyDataSetChanged()
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra("url",headlines.article_url)
            context?.startActivity(intent)
            headlines.read = true
        })

        return viewHolder.convertView!!
    }

    override fun getItem(position: Int): Any {
       return headlinesList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = headlinesList.size

    fun update(newData: ArrayList<Headlines>){
        headlinesList.addAll(0,newData)
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}