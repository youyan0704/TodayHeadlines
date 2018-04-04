package com.youyan.android.headlines.ui.fragement.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.widget.QMUIItemViewsAdapter

import com.youyan.android.headlines.R
import com.youyan.android.headlines.ui.fragement.home.RecommendFragment
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
        val titleResList: ArrayList<Int> = arrayListOf(R.string.recommend, R.string.location,
                R.string.video,R.string.hot_point, R.string.entertainment,
                R.string.new_era,R.string.qa, R.string.picture,
                R.string.science_technology,R.string.finance, R.string.car,
                R.string.sports,R.string.military, R.string.international,
                R.string.episode,R.string.odd_photos, R.string.street_beat,
                R.string.healthy,R.string.sale, R.string.house_property)
        val titleList = titleResList.map(this::getString)

        val fragments = ArrayList<Fragment>()
        for (i in 1.. titleList.size){
            fragments.add(RecommendFragment.newInstance(titleList[i-1]))
        }

        contentViewPager.adapter = HomeFragmentAdapter(fragments, titleList, fragmentManager)
        contentViewPager.offscreenPageLimit = titleList.size

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
