package com.youyan.android.headlines.injection.component

import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.ui.fragement.home.RecommendFragment
import dagger.Component

@Component(modules = arrayOf(LifecycleProviderModule::class))
open interface RecommendFragmentComponent {

    fun inject(fragment: RecommendFragment)
}