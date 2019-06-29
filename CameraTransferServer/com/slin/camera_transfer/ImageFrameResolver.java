package com.slin.camera_transfer;

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

    private byte[] frameTitleTag = new byte[TITLE_TAG_SIZE];

    public ImageFrameResolver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean checkTitle() throws IOException {
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
        ImageFrame imageFrame = new ImageFrame();
        resolveSize(imageFrame);
        resolveData(imageFrame);
        return imageFrame;
    }

    private void resolveData(ImageFrame frame) throws IOException {
        int length = readOneInt();
        frame.setLength(length);
        byte[] data = new byte[length];
        boolean isRead = inputStream.read(data) != -1;
        if(isRead){
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
        boolean isRead = inputStream.read(bytes) != -1;
        if (isRead) {
            result = bytesToInt(bytes);
        }
        return result;
    }

}
