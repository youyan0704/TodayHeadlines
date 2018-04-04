package com.youyan.android.headlines.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by android on 3/21/18.
 */

public class AppUtil {

    private AppUtil() {

        throw new UnsupportedOperationException("AppUtil cannot instantiated");
    }

    /**
     * 获取PackageManager对象
     * @param context
     * @return
     */
    private static PackageManager getPackageManager(Context context){
        return context.getPackageManager();
    }

    /**
     * 获取PckageInfo对象
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context){
        PackageManager packageManager = getPackageManager(context);
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app版本名
     */
    public static String getAppVersionName(Context context){

        return getPackageInfo(context) != null ? getPackageInfo(context).versionName : "";
    }

    /**
     * 获取app版本号
     */
    public static int getAppVersionCode(Context context){

        return  getPackageInfo(context) != null ? getPackageInfo(context).versionCode : 0;
    }

    /**
     * 获取App包名
     * @param context
     * @return
     */
    private static String getPackageName(Context context){
        return context.getPackageName();
    }
}
