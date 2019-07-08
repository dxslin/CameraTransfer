package com.slin.camera_transfer.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by slin on 2019-06-29.
 */
public class LogUtils {

    private static final Logger logger = Logger.getLogger("CameraTransfer");

    public static void i(String content) {
        logger.info(content);
    }

    public static void e(String msg) {
        logger.severe(msg);
    }

    public static void setLevel(Level level) {
        logger.setLevel(level);
    }

}
