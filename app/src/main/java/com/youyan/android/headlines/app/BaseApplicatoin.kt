package com.youyan.android.headlines.app

import android.app.Application

class BaseApplicatoin : Application() {

    override fun onCreate() {
        super.onCreate()
        initObjectBox()
    }

    private fun initObjectBox() {

    }
}