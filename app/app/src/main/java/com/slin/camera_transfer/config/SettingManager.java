package com.slin.camera_transfer.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.slin.camera_transfer.CameraTransferApplication;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class SettingManager {

    private static SettingManager manager;

    private SharedPreferences configPreferences;

    private SettingManager(Context context) {
        configPreferences = context.getSharedPreferences("setting_pref", Context.MODE_PRIVATE);
    }

    public static SettingManager getInstance() {
        if (manager == null) {
            synchronized (SettingManager.class) {
                if (manager == null) {
                    manager = new SettingManager(CameraTransferApplication.getAppContext());
                }
            }
        }
        return manager;
    }

    public void writeConfig(SettingConfig config) {
        SharedPreferences.Editor editor = configPreferences.edit();
        editor.putString("ip", config.ip);
        editor.putInt("port", config.port);
        editor.putInt("width", config.width);
        editor.putInt("height", config.height);
        editor.putBoolean("use_capture", config.useCapture);
        editor.apply();
    }

    public SettingConfig readConfig() {
        SettingConfig config = new SettingConfig();
        config.ip = configPreferences.getString("ip", "192.168.1.102");
        config.port = configPreferences.getInt("port", 4040);
        config.width = configPreferences.getInt("width", 320);
        config.height = configPreferences.getInt("height", 480);
        config.useCapture = configPreferences.getBoolean("use_capture", false);
        return config;
    }

}
