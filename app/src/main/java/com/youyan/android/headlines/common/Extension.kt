package com.youyan.android.headlines.common

import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.youyan.android.headlines.R
import com.youyan.android.headlines.app.BaseApplicatoin
import com.youyan.android.headlines.ui.model.UserInfo
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by android on 3/27/18.
 */
fun getHtml(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url(url)
            .build()

    val response = client.newCall(request).execute()

    return response.body()?.string() ?: ""
}

fun ImageView.loadUrl(url: String){
    Picasso.get()
            .load(Uri.parse(url))
            .placeholder(R.mipmap.app_logo)
            .into(this)
}

fun isLogined(): Boolean {

    return BaseApplicatoin.getBoxStoreInstance().boxFor(UserInfo::class.java).count() > 0
}

fun isProcessInBackground(context: Context): Boolean{

    var isInBackground = true
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
        val process = activityManager.runningAppProcesses
        for (pro in process){
            if (pro.processName == context.packageName){
                if (pro.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    for (currentPro in pro.pkgList){
                        if (currentPro == context.packageName){
                            isInBackground = false
                        }
                    }
                }
            }
        }
    }else{
        val taskInfo = activityManager.getRunningTasks(1)
        val component = taskInfo[0].topActivity
        if (component.packageName == context.packageName){
            isInBackground = false
        }
    }

    return isInBackground
}