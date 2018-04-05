package com.youyan.android.headlines.ui.fragement.home


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.youyan.android.headlines.R
import com.youyan.android.headlines.ui.adapter.RecommendItemAdapter
import com.youyan.android.headlines.ui.model.Recommend
import com.youyan.android.headlines.network.RecommendSource
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.model.NewsResponse
import com.youyan.android.headlines.ui.presenter.NewsPresenter
import com.youyan.android.headlines.ui.view.NewsView
import com.youyan.android.headlines.utils.LoggerUtil
import kotlinx.android.synthetic.main.fragment_recommend.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RecommendFragment: BaseFragment<NewsPresenter>(),NewsView {

    var recommendResources = ArrayList<Recommend>()
    lateinit var adapter: RecommendItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecommendItemAdapter(context,recommendResources)
        animationListView.adapter = adapter

    }

    override fun onGetNewsResponseResult(newsResponse: NewsResponse) {

        LoggerUtil.i("onGetNewsResponseResult",newsResponse.message)
        LoggerUtil.i("onGetNewsResponseResult",newsResponse.tips.display_info)

        for (data in newsResponse.data){
            LoggerUtil.i("onGetNewsData",data.content)
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        mBasePresenter = NewsPresenter()
        mBasePresenter.mBaseView = this


        if (isVisibleToUser && recommendResources.size == 0) {
//            load()
            mBasePresenter.getNewsResponse()
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
}// Required empty public constructor
