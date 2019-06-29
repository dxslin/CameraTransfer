package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;
import sun.rmi.runtime.Log;

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

    private static final int READ_LENGTH = 1024;

    private InputStream inputStream;

    private byte[] frameTitleTag = new byte[TITLE_TAG_SIZE];

    public ImageFrameResolver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean checkTitle() throws IOException {
        LogUtils.info("读取头部信息...");
        boolean result = inputStream.read(frameTitleTag) != -1;
        return result && new String(frameTitleTag).equals(TITLE_TAG);
    }

    /**
     * 协议解析<br>
     *    协议内容              长度
         camera_transfer		14                      <br>
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
        int size, offset = 0, lastOffset = length - READ_LENGTH;
        while (offset < lastOffset){
            size = inputStream.read(data, offset, READ_LENGTH);
            if(size == -1){
                break;
            } else {
                offset += size;
            }
        }
        //读取最后一段数据
        size = inputStream.read(data, offset, length - offset);
        if (size == (length - offset)) {
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
        int size = inputStream.read(bytes);
        if (size == 4) {
            result = bytesToInt(bytes);
        }
        return result;
    }

}
