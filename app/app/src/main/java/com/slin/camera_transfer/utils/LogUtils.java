package com.slin.camera_transfer.utils;

import android.util.Log;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class LogUtils {

    private static final String TAG = "camera_transfer";

    public static void i(String msg){
        Log.d(TAG, msg);
    }

    public static void w(String msg){
        Log.w(TAG, msg);
    }

    public static void e(String msg){
        Log.e(TAG, msg);
    }

}
