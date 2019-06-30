package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static com.slin.camera_transfer.ImageFrame.TITLE_TAG;
import static com.slin.camera_transfer.ImageFrame.TITLE_TAG_SIZE;
import static com.slin.camera_transfer.utils.Utils.bytesToInt;

/**
 * Created by slin on 2019-06-29.
 */
public class ImageFrameResolver {

    private InputStream inputStream;

    public ImageFrameResolver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean checkTitle() throws IOException {
        LogUtils.info("读取头部信息...");
        byte[] titleTag = new byte[TITLE_TAG_SIZE];
        boolean result = read(titleTag);
        return result && TITLE_TAG.equals(new String(titleTag));
    }

    /**
     * 协议解析<br>
     *    协议内容              长度
         camera_transfer		15                      <br>
         width			    	4                       <br>
         height				    4                       <br>
         imageDataLength  	    4                       <br>
         data buffer			imageDataLength
     * @return
     * @throws IOException
     */
    public ImageFrame resolve() throws IOException {
        LogUtils.info("开始读取...");
        ImageFrame imageFrame = new ImageFrame();
        resolveSize(imageFrame);
        resolveData(imageFrame);
        LogUtils.info("读取成功...");
        return imageFrame;
    }

    private void resolveData(ImageFrame frame) throws IOException {
        int length = readOneInt();
        frame.setLength(length);
        byte[] data = new byte[length];
        boolean readResult = read(data);
        if (readResult) {
            frame.setImage(ByteBuffer.wrap(data));
        }
    }

    private void resolveSize(ImageFrame frame) throws IOException {
        frame.setWidth(readOneInt());
        frame.setHeight(readOneInt());
    }

    private int readOneInt() throws IOException {
        int result = 0;
        byte[] bytes = new byte[4];
        boolean r = read(bytes);
        if (r) {
            result = bytesToInt(bytes);
        }
        return result;
    }

    /**
     * 读取数据，读满数组长度的数据为止，会阻塞
     *
     * @param b 返回数据
     * @return 读取是否成功
     * @throws IOException exception
     */
    private boolean read(@NotNull byte[] b) throws IOException {
        int offset = 0, length = b.length, size;
        while (offset < length) {
            size = inputStream.read(b, offset, length - offset);
            if (size < 0) {
                LogUtils.info("read end");
                return false;
            }
            offset += size;
        }
        return true;
    }

}
