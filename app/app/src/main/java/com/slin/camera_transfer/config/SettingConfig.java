package com.slin.camera_transfer.config;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class SettingConfig {

    public String ip;
    public int port;
    public int width;
    public int height;
    public boolean useCapture;

    @Override
    public String toString() {
        return "SettingConfig{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", width=" + width +
                ", height=" + height +
                ", useCapture=" + useCapture +
                '}';
    }
}
