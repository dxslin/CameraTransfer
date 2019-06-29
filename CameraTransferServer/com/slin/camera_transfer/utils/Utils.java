package com.slin.camera_transfer.utils;

import java.io.Closeable;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by slin on 2019-06-29.
 */
public class Utils {


    public static int bytesToInt(byte[] src, int offset) {
        if (src.length < 4) {
            throw new InvalidParameterException("src的长度必须大于4");
        }
        int value;
        value = (src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24);
        return value;
    }

    public static int bytesToInt(byte[] src){
        return bytesToInt(src, 0);
    }

    public static void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
