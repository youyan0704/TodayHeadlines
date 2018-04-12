package com.youyan.android.headlines.injection.component

import com.youyan.android.headlines.injection.module.LifecycleProviderModule
import com.youyan.android.headlines.injection.scope.PerCompomentScope
import com.youyan.android.headlines.ui.fragement.home.RecommendFragment
import dagger.Component

@PerCompomentScope
@Component(dependencies = arrayOf(FragmentComponent::class),modules = arrayOf(LifecycleProviderModule::class))
open interface RecommendFragmentComponent {

    fun inject(fragment: RecommendFragment)
}