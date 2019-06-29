package com.slin.camera_transfer.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class Utils {


    /**
     * int 转 byte数组
     * @param value value
     * @return byte[] bytes
     */
    public static byte[] intToBytes( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }

    /**
     * 关闭连接
     * @param closeable closeable
     */
    public static void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
