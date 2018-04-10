package com.youyan.android.headlines.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

@Module
class LifecycleProviderModule(private var lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun provideLifecycleProviderModule() : LifecycleProvider<*>{

        return lifecycleProvider
    }
}