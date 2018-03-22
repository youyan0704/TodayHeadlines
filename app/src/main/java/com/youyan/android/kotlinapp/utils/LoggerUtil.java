package com.youyan.android.kotlinapp.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by android on 3/21/18.
 * 日志类
 */

public final class LoggerUtil {

    private final static int LEVEL = 5;

    private final static String DEFAULT_TAG = "DEFAULT_LOGGER_TAG ";

    private LoggerUtil() {
        throw new UnsupportedOperationException("LoggerUtil cannot instantiated!");
    }

    public static void v(String tag,String msg) {
        if(LEVEL >= 5)  Log.v(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void d(String tag,String msg) {
        if(LEVEL >= 4)  Log.d(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void i(String tag,String msg) {
        if(LEVEL >= 3)  Log.i(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void w(String tag,String msg) {
        if(LEVEL >= 2)  Log.w(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void e(String tag,String msg) {
        if(LEVEL >= 1)  Log.e(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }
}
