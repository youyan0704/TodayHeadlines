package com.youyan.android.headlines.app

import android.app.Application
import com.mob.MobSDK
import com.youyan.android.headlines.BuildConfig
import com.youyan.android.headlines.ui.model.MyObjectBox
import com.youyan.android.headlines.utils.LoggerUtil
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

class BaseApplicatoin : Application() {

    override fun onCreate() {
        super.onCreate()
        MobSDK.init(this)
        initObjectBox()
    }

    companion object {
        lateinit var boxStore: BoxStore
        fun getBoxStoreInstance() = boxStore
    }

    private fun initObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(this).build()
        if (BuildConfig.DEBUG){
            boxStore?.let {
                LoggerUtil.i("ObjectBrowser", "Started: " + System.currentTimeMillis())
                val started = AndroidObjectBrowser(boxStore).start(this)
            }
        }
    }
}