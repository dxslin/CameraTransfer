package com.slin.camera_transfer;

import android.app.Application;
import android.content.Context;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class CameraTransferApplication extends Application {

    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        appContext = base;
    }
}
