package com.youyan.android.headlines.app

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

class AppManager private constructor() {

    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    fun removeActivity(activity: Activity){
        if (activityStack.contains(activity)){
            activity.finish()
            activityStack.remove(activity)
        }
    }

    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    fun clearAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    fun exitApp(context: Context){
        clearAllActivity()
        val acticityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        acticityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}