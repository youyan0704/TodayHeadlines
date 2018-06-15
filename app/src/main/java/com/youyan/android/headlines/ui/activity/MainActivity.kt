package com.youyan.android.headlines.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.youyan.android.headlines.R
import com.youyan.android.headlines.app.AppManager
import com.youyan.android.headlines.app.BaseApplication
import com.youyan.android.headlines.common.initStatusBarDarkMode
import com.youyan.android.headlines.common.initStatusBarLightMode
import com.youyan.android.headlines.common.isLogin
import com.youyan.android.headlines.common.loadUrl
import com.youyan.android.headlines.ui.fragement.main.HomeFragment
import com.youyan.android.headlines.ui.fragement.main.MiniHeadlinesFragment
import com.youyan.android.headlines.ui.fragement.main.MiniVideoFragment
import com.youyan.android.headlines.ui.fragement.main.XiGuaFragment
import com.youyan.android.headlines.reflect.BottomNavigationViewHelper
import com.youyan.android.headlines.ui.base.BaseActivity
import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.UserInfo
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<BasePresenter<*>>(),View.OnClickListener {

    private var homeFragment: HomeFragment? = null
    private var xiguaFragment: XiGuaFragment? = null
    private var miniHeadlinesFragment: MiniHeadlinesFragment? = null
    private var miniVideoFragment: MiniVideoFragment? = null
    private var exitTime: Long = 0
    private lateinit var userInfo: UserInfo
    private lateinit var userInfoBox: Box<UserInfo>


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_xigua_video -> {
                switchFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mini_headlines -> {
                switchFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mini_video -> {
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
        switchFragment(0)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)

        initView()
        initData()

    }

    private fun initView() {
        id_me.setOnClickListener(this)
        id_search.setOnClickListener(this)
        id_publish.setOnClickListener(this)

    }

    private fun initData() {
        userInfoBox = BaseApplication.getBoxStoreInstance().boxFor(UserInfo::class.java)
        userInfo = UserInfo(0,"http://cdnq.duitang.com/uploads/item/201504/04/20150404H3338_N8Wir.jpeg","",
                true,99,"Allen", 1201230011,false)
        userInfoBox.put(userInfo)
//        userInfoBox.removeAll()

        if (isLogin()){
            userInfo = userInfoBox.all.first()
            loginedIcon.visibility = View.VISIBLE
            unLoginedIcon.visibility = View.GONE
            unLoginedText.visibility = View.GONE
            loginedIcon.loadUrl(userInfo.avatar_url,R.mipmap.a9v)
        }else{
            loginedIcon.visibility = View.GONE
            unLoginedIcon.visibility = View.VISIBLE
            unLoginedText.visibility = View.VISIBLE
        }


    }

    private fun setToolbar(i: Int){
        when(i){
            0,1 -> {
                initStatusBarDarkMode(this)
                if (toolbar.visibility == View.GONE) toolbar.visibility = View.VISIBLE
            }
            2,3 -> {
                initStatusBarLightMode(this)
                if (toolbar.visibility == View.VISIBLE) toolbar.visibility = View.GONE
            }
        }
    }

    private fun switchFragment(i: Int) {
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        hiddenAllFragments(beginTransaction)
        when (i) {
            0 -> {
                setToolbar(0)
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    beginTransaction.add(R.id.content_main, homeFragment, "homeFragment")
                } else {
                    beginTransaction.show(homeFragment)
                }
            }
            1 -> {
                setToolbar(1)
                if (xiguaFragment == null) {
                    xiguaFragment = XiGuaFragment()
                    beginTransaction.add(R.id.content_main, xiguaFragment, "xiguaFragment")
                } else {
                    beginTransaction.show(xiguaFragment)
                }
            }
            2 -> {
                setToolbar(2)
                if (miniHeadlinesFragment == null) {
                    miniHeadlinesFragment = MiniHeadlinesFragment.newInstance()
                    beginTransaction.add(R.id.content_main, miniHeadlinesFragment, "miniHeadlinesFragment")
                } else {
                    beginTransaction.show(miniHeadlinesFragment)
                }
            }
            3 -> {
                setToolbar(3)
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

    private fun hiddenAllFragments(beginTransaction: android.support.v4.app.FragmentTransaction) {
        if (homeFragment != null) beginTransaction.hide(homeFragment)
        if (xiguaFragment != null) beginTransaction.hide(xiguaFragment)
        if (miniHeadlinesFragment != null) beginTransaction.hide(miniHeadlinesFragment)
        if (miniVideoFragment != null) beginTransaction.hide(miniVideoFragment)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.id_me -> {
                startActivity<UserInfoActivity>()
            }
            R.id.id_publish ->{
                toast("稍后发布....")
            }
        }
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - exitTime > 2000){
            toast("再按一次退出程序")
            exitTime = time
        }else{
            AppManager.instance.exitApp(this)
        }

    }
}
