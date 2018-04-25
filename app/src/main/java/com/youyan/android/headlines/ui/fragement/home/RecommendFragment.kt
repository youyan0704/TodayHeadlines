package com.youyan.android.headlines.ui.fragement.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout

import com.youyan.android.headlines.R
import com.youyan.android.headlines.injection.component.DaggerRecommendFragmentComponent
import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.adapter.RecommendItemAdapter
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.ui.presenter.NewsPresenter
import com.youyan.android.headlines.ui.view.NewsView
import com.youyan.android.headlines.utils.LoggerUtil
import kotlinx.android.synthetic.main.fragment_recommend.*

class RecommendFragment: BaseFragment<NewsPresenter>(),NewsView {

    var recommendResources = ArrayList<NewsData>()
    lateinit var adapter: RecommendItemAdapter
    var isQMUIPullRefreshLayoutVisiable: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullRefreshLayout.setOnPullListener(object : QMUIPullRefreshLayout.OnPullListener {
            override fun onMoveRefreshView(offset: Int) {}

            override fun onRefresh() {
                isQMUIPullRefreshLayoutVisiable = true
                mBasePresenter.getNewsResponse()
            }

            override fun onMoveTarget(offset: Int) {}

        })

        animationListView.

        adapter = RecommendItemAdapter(activity!!.applicationContext,recommendResources)
        animationListView.adapter = adapter
        
/*        adapter.setOnItemClickListener(object : RecommendItemAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
            }
            
        })*/

    }

    private fun initInjection() {
        DaggerRecommendFragmentComponent.builder()
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
                .inject(this)

        mBasePresenter.mBaseView = this

    }

    override fun onGetNewsResponseResult(newsDataList: ArrayList<NewsData>) {

        if (isQMUIPullRefreshLayoutVisiable){
            isQMUIPullRefreshLayoutVisiable = false
            pullRefreshLayout.finishRefresh()
        }

        recommendResources = newsDataList
        adapter.update(recommendResources)
        LoggerUtil.i("TAG",adapter.getItem(0).toString())


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        initInjection()

        if (isVisibleToUser && recommendResources.size == 0) {
            mBasePresenter.getNewsResponse()
        }
    }

}// Required empty public constructor
