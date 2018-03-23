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
        val titleResList: ArrayList<Int> = arrayListOf(R.string.title_xigua_video, R.string.title_mini_headlines, R.string.title_mini_video)
        val titleList = titleResList.map(this::getString)

        val fragments = ArrayList<Fragment>()
        fragments.add(XiGuaFragment())
        fragments.add(MiniHeadlinesFragment())
        fragments.add(MiniVideoFragment())

        contentViewPager.adapter = HomeFragmentAdapter(fragments, titleList,fragmentManager)
        contentViewPager.offscreenPageLimit = 2

        tabSegment.run {
            setupWithViewPager(contentViewPager)

        }

    }

    class HomeFragmentAdapter(val fragments: List<Fragment>,
                              val nameList: List<String>,
                              fm: FragmentManager?) : FragmentPagerAdapter(fm){

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = nameList[position]

    }
}
