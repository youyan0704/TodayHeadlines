package com.youyan.android.headlines.injection.module

import android.app.Activity
import com.youyan.android.headlines.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity){

    @ActivityScope
    @Provides
    fun provideActivityModule():Activity{
        return activity
    }
}