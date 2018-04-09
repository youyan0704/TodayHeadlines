package com.youyan.android.headlines.injection.component

import com.youyan.android.headlines.ui.fragement.home.RecommendFragment
import dagger.Component

@Component
open interface RecommendFragmentComponent {

    fun inject(fragment: RecommendFragment)
}