package com.youyan.android.kotlinapp.network

/**
 * Created by android on 3/27/18.
 */
interface Source<out T> {
    fun get(): T
}
