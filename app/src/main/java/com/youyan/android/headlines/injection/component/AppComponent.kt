package com.youyan.android.headlines.injection.component

import android.content.Context
import com.youyan.android.headlines.injection.module.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ContextModule::class))
open interface AppComponent {
    fun context():Context
}