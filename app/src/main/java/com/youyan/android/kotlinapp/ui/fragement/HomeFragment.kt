package com.youyan.android.kotlinapp.ui.fragement


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.QMUITabSegment

import com.youyan.android.kotlinapp.R
import com.youyan.android.kotlinapp.utils.LoggerUtil
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabSegment()
    }

    private fun initTabSegment() {
        val nameResList: ArrayList<Int> = arrayListOf(R.string.title_xigua_video, R.string.title_mini_headlines, R.string.title_mini_video)
        val fragments = ArrayList<Fragment>()
        fragments.add(XiGuaFragment())
        fragments.add(MiniHeadlinesFragment())
        fragments.add(MiniVideoFragment())


        val nameList = nameResList.map(this::getString)

        LoggerUtil.i("TAG","initTabSegment")
        tabSegment.setHasIndicator(true);
        tabSegment.setIndicatorPosition(false);
/*        tabSegment.setIndicatorWidthAdjustContent(true);
        tabSegment.addTab(QMUITabSegment.Tab("推荐"))
        tabSegment.addTab(QMUITabSegment.Tab("热点"))
        tabSegment.addTab(QMUITabSegment.Tab("深圳"))
        tabSegment.addTab(QMUITabSegment.Tab("视频"))
        tabSegment.addTab(QMUITabSegment.Tab("新时代"))
        tabSegment.addTab(QMUITabSegment.Tab("图片"))
        tabSegment.addTab(QMUITabSegment.Tab("娱乐"))
        tabSegment.addTab(QMUITabSegment.Tab("问答"))
        tabSegment.addTab(QMUITabSegment.Tab("科技"))*/
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        contentViewPager.adapter = HomeFragmentAdaptr(fragments, nameList,supportFragmentManager)
        tabSegment.setupWithViewPager(contentViewPager, false)

    }

    class HomeFragmentAdaptr(val fragments: List<Fragment>,
                             val nameList: List<String>,
                             fm: FragmentManager) : FragmentPagerAdapter(fm){

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = nameList[position]

    }
}
