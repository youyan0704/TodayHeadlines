package com.youyan.android.headlines.ui.activity

import android.os.Bundle
import com.youyan.android.headlines.R
import com.youyan.android.headlines.ui.base.BaseActivity
import com.youyan.android.headlines.ui.base.BasePresenter

class UserInfoActivity : BaseActivity<BasePresenter<*>>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }
}
