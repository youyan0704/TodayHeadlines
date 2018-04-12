package com.youyan.android.headlines.injection.module

import android.app.Activity
import android.app.Fragment
import com.youyan.android.headlines.injection.scope.ActivityScope
import com.youyan.android.headlines.injection.scope.FragmentScope
import com.youyan.android.headlines.ui.base.BaseFragment
import com.youyan.android.headlines.ui.base.BasePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>){

    @FragmentScope
    @Provides
    fun provideFragmentModule(): BaseFragment<*> {
        return fragment
    }
}