package com.youyan.android.headlines.injection.component

import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.fragement.home.RecommendFragment
import com.youyan.android.headlines.ui.fragement.main.MiniHeadlinesFragment
import dagger.Component

@Component(modules = arrayOf(LifecycleProviderModule::class))
open interface RecommendFragmentComponent {

    fun inject(fragment: RecommendFragment)
    fun inject(fragment: MiniHeadlinesFragment)
}