package com.youyan.android.kotlinapp.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.youyan.android.kotlinapp.R
import com.youyan.android.kotlinapp.ui.fragement.HomeFragment
import com.youyan.android.kotlinapp.ui.fragement.MiniHeadlinesFragment
import com.youyan.android.kotlinapp.ui.fragement.MiniVideoFragment
import com.youyan.android.kotlinapp.ui.fragement.XiGuaFragment
import com.youyan.android.kotlinapp.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var homeFragment: HomeFragment? = null
    private var xiguaFragment: XiGuaFragment? = null
    private var miniHeadlinesFragment: MiniHeadlinesFragment? = null
    private var miniVideoFragment: MiniVideoFragment? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setToolbar(0)
                switchFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_xigua_video -> {
                setToolbar(1)
                switchFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mini_headlines -> {
                setToolbar(2)
                switchFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mini_video -> {
                setToolbar(3)
                switchFragment(3)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)

        setDefaultFragment()
    }

    private fun setToolbar(i: Int){
        when(i){
            0,1 -> {
                if (toolbar.visibility == View.GONE) toolbar.visibility = View.VISIBLE
            }
            2,3 -> {
                if (toolbar.visibility == View.VISIBLE) toolbar.visibility = View.GONE
            }
        }
    }

    private fun switchFragment(i: Int) {
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        hidenAllFragments(beginTransaction)
        when (i) {
            0 -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    beginTransaction.add(R.id.content_main, homeFragment, "homeFragment")
                } else {
                    beginTransaction.show(homeFragment)
                }
            }
            1 -> {
                if (xiguaFragment == null) {
                    xiguaFragment = XiGuaFragment()
                    beginTransaction.add(R.id.content_main, xiguaFragment, "xiguaFragment")
                } else {
                    beginTransaction.show(xiguaFragment)
                }
            }
            2 -> {
                if (miniHeadlinesFragment == null) {
                    miniHeadlinesFragment = MiniHeadlinesFragment()
                    beginTransaction.add(R.id.content_main, miniHeadlinesFragment, "miniHeadlinesFragment")
                } else {
                    beginTransaction.show(miniHeadlinesFragment)
                }
            }
            3 -> {
                if (miniVideoFragment == null) {
                    miniVideoFragment = MiniVideoFragment()
                    beginTransaction.add(R.id.content_main, miniVideoFragment, "miniVideoFragment")
                } else {
                    beginTransaction.show(miniVideoFragment)
                }
            }
        }
        beginTransaction.commit()
    }

    private fun setDefaultFragment() {
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        if (homeFragment == null) {
            homeFragment = HomeFragment()
            beginTransaction.add(R.id.content_main, homeFragment, "homeFragment")
        } else {
            beginTransaction.show(homeFragment)
        }
        beginTransaction.commit()
    }

    private fun hidenAllFragments(beginTransaction: android.support.v4.app.FragmentTransaction) {
        if (homeFragment != null) beginTransaction.hide(homeFragment)
        if (xiguaFragment != null) beginTransaction.hide(xiguaFragment)
        if (miniHeadlinesFragment != null) beginTransaction.hide(miniHeadlinesFragment)
        if (miniVideoFragment != null) beginTransaction.hide(miniVideoFragment)
    }
}
