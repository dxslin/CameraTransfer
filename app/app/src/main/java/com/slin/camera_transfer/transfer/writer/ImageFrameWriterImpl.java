package com.slin.camera_transfer.transfer.writer;

import com.slin.camera_transfer.model.ImageFrame;
import com.slin.camera_transfer.utils.LogUtils;
import com.slin.camera_transfer.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class ImageFrameWriterImpl implements ImageFrameWriter {

    private static final int WRITE_LENGTH = 1024;

    private ImageFrame imageFrame;
    private OutputStream outputStream;

    public ImageFrameWriterImpl(ImageFrame imageFrame, OutputStream outputStream){
        this.imageFrame = imageFrame;
//        this.outputStream = new BufferedOutputStream(outputStream);
        this.outputStream = outputStream;
    }

    /**
     * 发送格式
     *    协议内容              长度
         camera_transfer		14                      <br>
         width			    	4                       <br>
         height				    4                       <br>
         imageDataLength  	    4                       <br>
         data buffer			imageDataLength
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        LogUtils.i("write: " + imageFrame);
        outputStream.write(imageFrame.getTitleTag());
        writeInt(imageFrame.getWidth());
        writeInt(imageFrame.getHeight());
        writeInt(imageFrame.getLength());
        byte[] data = imageFrame.getImageBuffer().array();

        int offset = 0, lastOffset = data.length - WRITE_LENGTH;
        while (offset < lastOffset){
            outputStream.write(data, offset, WRITE_LENGTH);
            offset += WRITE_LENGTH;
        }
        outputStream.write(data, offset, data.length - offset);
        outputStream.flush();
//        outputStream.close();
    }

    private void writeInt(int value) throws IOException {
        outputStream.write(Utils.intToBytes(value));
    }

}
