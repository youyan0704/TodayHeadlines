package com.youyan.android.headlines.ui.fragement.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout

import com.youyan.android.headlines.R
import com.youyan.android.headlines.injection.component.DaggerRecommendFragmentComponent
import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.ItemDecoration.RecyclerViewDivider
import com.youyan.android.headlines.ui.activity.WebViewActivity
import com.youyan.android.headlines.ui.adapter.MiniHeadlinesAdapter
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.model.HeadlinesResponse
import com.youyan.android.headlines.ui.model.MiniHeadlines
import com.youyan.android.headlines.ui.presenter.MiniHeadlinesPresenter
import com.youyan.android.headlines.ui.view.MiniHeadlinesView
import kotlinx.android.synthetic.main.fragment_mini_headlines.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class MiniHeadlinesFragment : BaseFragment<MiniHeadlinesPresenter>(),MiniHeadlinesView,View.OnClickListener {

    private lateinit var miniHeadlinesAdapter: MiniHeadlinesAdapter
    private var miniHeadlines = ArrayList<MiniHeadlines>()
    private var isPullUpRefresh = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
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
                mBasePresenter.getMiniHeadlinesResponse()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(object : RecyclerViewDivider(context,LinearLayoutManager.HORIZONTAL,
                20,0xFAFAFA){})
        miniHeadlinesAdapter = MiniHeadlinesAdapter(R.layout.fragment_mini_headlines_item, miniHeadlines)
        recyclerView.adapter = miniHeadlinesAdapter
        //开启默认动画
        miniHeadlinesAdapter.openLoadAnimation()
        /*//开启上拉加载更多
        miniHeadlinesAdapter.setEnableLoadMore(true)
        //当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        miniHeadlinesAdapter.setPreLoadNumber(2)
        //加载更多视图
        miniHeadlinesAdapter.setLoadMoreView(object : MyLoadMoreView() {})
        //加载更多
        miniHeadlinesAdapter.setOnLoadMoreListener({
            isPullUpRefresh = true
            initData()

        },recyclerView)*/
        //点击事件
        miniHeadlinesAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            startActivity<WebViewActivity>("url" to miniHeadlines[position].share_url) }
        //子view点击事件
        miniHeadlinesAdapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener{ adapter, view, position ->
            when(view.id){
                R.id.dislike -> {
                    adapter.remove(position)
                }
            }
        }
    }

    private fun initData() {
        miniHeadlinesAdapter.emptyView = loadingView
        mBasePresenter.getMiniHeadlinesResponse()
    }

    override fun onGetMiniHeadlinesResponseResult(headlinesResponse: HeadlinesResponse) {
        pullRefreshLayout.finishRefresh()
        for (data in headlinesResponse.data){
            val miniHeadline = Gson().fromJson(data.content,MiniHeadlines::class.java)
            miniHeadlines.add(0,miniHeadline)
        }
        if (isPullUpRefresh){
            miniHeadlinesAdapter.addData(miniHeadlines)
            miniHeadlinesAdapter.loadMoreComplete()

        } else{
            miniHeadlinesAdapter.setNewData(miniHeadlines)
        }

    }

    override fun onGetResultError() {
        pullRefreshLayout.finishRefresh()
        miniHeadlinesAdapter.emptyView = errorView
        errorView.setOnClickListener {
            initData()
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.find_friend -> {
                toast("找人")
            }
            R.id.id_publish -> {
                toast("发布")
            }
        }
    }

    companion object {
        fun newInstance(): MiniHeadlinesFragment {
            return MiniHeadlinesFragment()
        }
    }

}
