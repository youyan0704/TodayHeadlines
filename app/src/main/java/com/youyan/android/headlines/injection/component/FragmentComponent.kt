package com.youyan.android.headlines.injection.component

import android.app.Activity
import android.app.Fragment
import android.content.Context
import com.youyan.android.headlines.injection.module.ActivityModule
import com.youyan.android.headlines.injection.module.ContextModule
import com.youyan.android.headlines.injection.module.FragmentModule
import com.youyan.android.headlines.injection.scope.ActivityScope
import com.youyan.android.headlines.injection.scope.FragmentScope
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.base.BasePresenter
import dagger.Component
import javax.inject.Singleton

@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(FragmentModule::class))
open interface FragmentComponent {
    fun fragment(): BaseFragment<*>
}