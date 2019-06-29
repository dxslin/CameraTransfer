package com.slin.camera_transfer.utils;

import java.util.logging.Logger;

/**
 * Created by slin on 2019-06-29.
 */
public class LogUtils {
    private static final Logger logger = Logger.getLogger("camera_transfer");

    public static void info(String content){
        logger.info(content);
    }

}
