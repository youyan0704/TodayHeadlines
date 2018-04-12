package com.youyan.android.headlines.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private var context: Context) {

    @Provides
    @Singleton
    fun provideContextModule() : Context {

        return context
    }
}