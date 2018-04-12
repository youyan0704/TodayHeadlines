package com.youyan.android.headlines.injection.component

import android.app.Activity
import android.content.Context
import com.youyan.android.headlines.injection.module.ActivityModule
import com.youyan.android.headlines.injection.module.ContextModule
import com.youyan.android.headlines.injection.scope.ActivityScope
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
open interface ActivityComponent {
    fun activity():Activity
}