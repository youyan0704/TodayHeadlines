package com.youyan.android.headlines.ui.fragement.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout

import com.youyan.android.headlines.R
import com.youyan.android.headlines.injection.component.DaggerRecommendFragmentComponent
import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.adapter.MiniHeadlinesAdapter
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.ui.presenter.WeiTouTiaoPresenter
import com.youyan.android.headlines.ui.view.WeiTouTiaoView
import com.youyan.android.headlines.utils.LoggerUtil
import kotlinx.android.synthetic.main.fragment_mini_headlines.*

class MiniHeadlinesFragment : BaseFragment<WeiTouTiaoPresenter>(),WeiTouTiaoView,View.OnClickListener {

    private lateinit var miniHeadlinesAdapter: MiniHeadlinesAdapter
    private var newsDatas = ArrayList<NewsData>()
    private var isQMUIPullRefreshLayoutVisiable: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mini_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initInjection()
        initData()
    }

    private fun initInjection() {
        DaggerRecommendFragmentComponent.builder()
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
                .inject(this)

        mBasePresenter.mBaseView = this
    }

    private fun initView() {
        find_friend.setOnClickListener(this)
        id_publish.setOnClickListener(this)
        pullRefreshLayout.setOnPullListener(object : QMUIPullRefreshLayout.OnPullListener {
            override fun onMoveRefreshView(offset: Int) {}

            override fun onMoveTarget(offset: Int) {}

            override fun onRefresh() {
                isQMUIPullRefreshLayoutVisiable = true
                mBasePresenter.getWeiTouTiaoResponse()
            }

        })
    }

    private fun initData() {

        recyclerView.layoutManager = LinearLayoutManager(context)
        miniHeadlinesAdapter = MiniHeadlinesAdapter(R.layout.fragment_mini_headlines_item, newsDatas)
        recyclerView.adapter = miniHeadlinesAdapter
        mBasePresenter.getWeiTouTiaoResponse()
    }

    override fun onGetWeiResponseResult(newsDataList: ArrayList<NewsData>) {
        if (isQMUIPullRefreshLayoutVisiable){
            isQMUIPullRefreshLayoutVisiable = false
            pullRefreshLayout.finishRefresh()
        }
        LoggerUtil.i("newsDataList",newsDataList.size.toString())
        newsDatas = newsDataList
        miniHeadlinesAdapter.setNewData(newsDatas)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.find_friend -> {

            }
            R.id.id_publish -> {

            }
        }
    }

    companion object {
        fun newInstance(): MiniHeadlinesFragment {
            return MiniHeadlinesFragment()
        }
    }

}
