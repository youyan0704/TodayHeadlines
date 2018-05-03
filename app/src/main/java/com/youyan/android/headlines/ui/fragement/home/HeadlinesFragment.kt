package com.youyan.android.headlines.ui.fragement.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout

import com.youyan.android.headlines.R
import com.youyan.android.headlines.injection.component.DaggerRecommendFragmentComponent
import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.adapter.HeadlinesAdapter
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.model.Headlines
import com.youyan.android.headlines.ui.presenter.HeadlinesPresenter
import com.youyan.android.headlines.ui.view.HeadlinesView
import kotlinx.android.synthetic.main.fragment_recommend.*

class HeadlinesFragment: BaseFragment<HeadlinesPresenter>(),HeadlinesView {

    private var headlinesRes = ArrayList<Headlines>()
    private lateinit var adapter: HeadlinesAdapter
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


        adapter = HeadlinesAdapter(context,headlinesRes)
        animationListView.adapter = adapter
        
/*        adapter.setOnItemClickListener(object : HeadlinesAdapter.OnItemClickListener {
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

    override fun onGetHeadlinesResponseResult(headlinesList: ArrayList<Headlines>) {
        if (isQMUIPullRefreshLayoutVisiable){
            isQMUIPullRefreshLayoutVisiable = false
            pullRefreshLayout.finishRefresh()
        }

        headlinesRes = headlinesList
        adapter.update(headlinesRes)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        initInjection()

        if (isVisibleToUser && headlinesRes.size == 0) {
            mBasePresenter.getNewsResponse()
        }
    }

}// Required empty public constructor
