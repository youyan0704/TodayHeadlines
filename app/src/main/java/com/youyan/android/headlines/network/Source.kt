package com.youyan.android.headlines.network

/**
 * Created by android on 3/27/18.
 */
interface Source<out T> {
    fun get(): T
}
