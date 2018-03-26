package com.youyan.android.kotlinapp.adapter

/**
 * Created by android on 11/20/17.
 */

import android.content.Context
import android.net.Uri
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.youyan.android.kotlinapp.R

/**
 * 通用的ViewHolder
 *
 * @author owen
 */
class CommonViewHolder
/**
 * 私有化的构造函数，有类内部来管理该实例
 *
 * @param context 上下文对象
 * @param itemLayoutResId item的布局文件的资源ID
 * @param position BaseAdapter.getView()的传入参数
 * @param parent BaseAdapter.getView()的传入参数
 */
private constructor(context: Context?, itemLayoutResId: Int, position: Int, parent: ViewGroup?) {

    /**
     * 存储item中所用控件引用的容器
     *
     * Key - 资源ID
     * Value - 控件的引用
     */
    private var views: SparseArray<View>? = null

    var convertView: View? = null

    private var position = 0

    init {
        this.views = SparseArray()
        this.position = position
        this.convertView = LayoutInflater.from(context).inflate(itemLayoutResId, parent, false)

        convertView!!.tag = this
    }

    /**
     * 【核心部分】
     * 根据控件的资源ID，获取控件
     *
     * @param viewResId 控件的资源ID
     * @return 控件的引用
     */
    fun <T : View> getView(viewResId: Int): T? {
        var view: View? = views!!.get(viewResId)

        if (view == null) {
            view = convertView!!.findViewById(viewResId)
            views!!.put(viewResId, view)
        }

        return view as T?
    }

    fun setText(viewResId: Int, text: String): CommonViewHolder {
        val tv = getView<TextView>(viewResId)
        tv!!.text = text

        return this
    }

    fun setTextColor(viewResId: Int, ResId: Int): CommonViewHolder {
        val tv = getView<TextView>(viewResId)
        tv!!.setTextColor(ResId)

        return this
    }

    fun setImageResource(viewResId: Int, ResId: Int): CommonViewHolder {
        val iv = getView<ImageView>(viewResId)
        iv!!.setImageResource(ResId)

        return this
    }


    fun setImageFromUrl(viewResId: Int, uri: Uri): CommonViewHolder {
        val iv = getView<ImageView>(viewResId)
        Picasso.get()
                .load(uri)
                .placeholder(R.mipmap.app_logo)
                .into(iv)

        return this
    }

    /*    public SlideTouchView setSwipeLayouyt(int viewResId){
        SlideTouchView slideTouchView = getView(viewResId);

        return slideTouchView;
    }*/

    fun setChecked(viewResId: Int, isChecked: Boolean): CommonViewHolder {
        val checkBox = getView<CheckBox>(viewResId)
        checkBox!!.isChecked = if (isChecked) true else false

        return this
    }

    fun isVisiable(viewResId: Int, isVisiable: Int): CommonViewHolder{
        val view = getView<View>(viewResId)
        view!!.visibility = isVisiable
        return this
    }

    companion object {

        /**
         * 得到一个ViewHolder对象
         *
         * @param context 上下文对象
         * @param itemLayoutResId item的布局文件的资源ID
         * @param position BaseAdapter.getView()的传入参数
         * @param convertView BaseAdapter.getView()的传入参数
         * @param parent BaseAdapter.getView()的传入参数
         * @return 一个ViewHolder对象
         */
        fun getViewHolder(context: Context?, itemLayoutResId: Int, position: Int,
                          convertView: View?, parent: ViewGroup?): CommonViewHolder {
            if (convertView == null) {
                return CommonViewHolder(context, itemLayoutResId, position, parent)
            } else {
                val viewHolder = convertView.tag as CommonViewHolder
                viewHolder.position = position // 这里要更新一下position，因为position一直发生变化
                return viewHolder
            }
        }
    }
}
