package com.youyan.android.kotlinapp.ui.fragement.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.youyan.android.kotlinapp.R
import com.youyan.android.kotlinapp.adapter.RecommendItemAdapter
import com.youyan.android.kotlinapp.model.Recommend
import kotlinx.android.synthetic.main.fragment_recommend.*


/**
 * A simple [Fragment] subclass.
 * Use the [RecommendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecommendFragment : Fragment() {

    private var title: String? = null

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
        val recommends = ArrayList<Recommend>()
        for (i in 1..15){
            recommends.add(Recommend(1,"中国最危险的村长：四周被悬崖绝壁保卫，进出只靠一个铁笼子","http://i.imgur.com/DvpvklR.png",
                    "热","人民日报","144评论","03-25 10:12",false))
        }
        animationListView.adapter = RecommendItemAdapter(context,recommends)
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
