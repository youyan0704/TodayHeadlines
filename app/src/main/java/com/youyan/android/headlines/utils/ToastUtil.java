package com.youyan.android.headlines.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by android on 3/21/18.
 * Toast类
 */

public class ToastUtil {

    private final static boolean isShow = true;

    private ToastUtil(){
        throw new UnsupportedOperationException("ToastUtil cannot be instantiated");
    }

    public static void showShort(Context context, CharSequence message) {
        if(isShow)  Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int message){
        if (isShow) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context,CharSequence message) {
        if(isShow)  Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context,int message) {
        if(isShow)  Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context, int message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }
}
