package com.youyan.android.headlines.app

import android.app.Application
import com.youyan.android.headlines.injection.component.AppComponent
import com.youyan.android.headlines.injection.component.DaggerAppComponent
import com.youyan.android.headlines.injection.module.ContextModule

class BaseApplicatoin : Application() {

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}