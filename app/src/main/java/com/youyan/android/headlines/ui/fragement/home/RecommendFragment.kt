package com.youyan.android.headlines.ui.fragement.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.QMUIAnimationListView

import com.youyan.android.headlines.R
import com.youyan.android.headlines.adapter.RecommendItemAdapter
import com.youyan.android.headlines.model.Recommend
import com.youyan.android.headlines.network.RecommendSource
import com.youyan.android.headlines.utils.LoggerUtil
import kotlinx.android.synthetic.main.fragment_recommend.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.
 * Use the [RecommendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecommendFragment : Fragment() {

    private var title: String? = null
    var recommendResources = ArrayList<Recommend>()
    lateinit var adapter: RecommendItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = arguments!!.getString(ARG_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*for (i in 1..15){
            recommends.add(Recommend(1,"中国最危险的村长：四周被悬崖绝壁保卫，进出只靠一个铁笼子",
                    "http://p1.pstatp.com/large/pgc-image/1521900422569f610a0908a",
                    "热","人民日报","144评论","03-25 10:12",false))
        }*/
        LoggerUtil.i("adapter","load RecommendItemAdapter")
        adapter = RecommendItemAdapter(context,recommendResources)
        animationListView.adapter = adapter

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && recommendResources.size == 0) {

            LoggerUtil.i("load","load recommendResources")
            load()
        }
    }

    private fun load() {
        doAsync {
            val data = RecommendSource().get()
            uiThread {
                adapter.update(data)

            }
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_TITLE = "title"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment RecommendFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(title: String): RecommendFragment {
            val fragment = RecommendFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
