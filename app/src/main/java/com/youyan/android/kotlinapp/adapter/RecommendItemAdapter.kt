package com.youyan.android.kotlinapp.adapter

import android.content.Context
import android.content.res.Resources
import android.database.DataSetObserver
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.qmuiteam.qmui.widget.QMUIAnimationListView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.youyan.android.kotlinapp.R
import com.youyan.android.kotlinapp.model.Recommend

/**
 * Created by android on 3/26/18.
 */
class RecommendItemAdapter(val context: Context?,
                           val recommends: ArrayList<Recommend>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder = CommonViewHolder.getViewHolder(context, R.layout.fragment_recommend_item, position, convertView, parent)
        viewHolder.setText(R.id.title,recommends[position].title?:"")
        viewHolder.setTextColor(R.id.title,if (recommends[position].read) Color.GRAY else Color.BLACK)
        viewHolder.setImageFromUrl(R.id.image,Uri.parse(recommends[position].image?:""))
        viewHolder.setText(R.id.category,recommends[position].category?:"")
        viewHolder.isVisiable(R.id.category,if (recommends[position].category.isEmpty()) View.INVISIBLE else View.VISIBLE)
        viewHolder.setText(R.id.source,recommends[position].source?:"")
        viewHolder.setText(R.id.comment,recommends[position].comment?:"")
        viewHolder.setText(R.id.publishTime,recommends[position].publishTime?:"")
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

}