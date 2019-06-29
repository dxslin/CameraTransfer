package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;

import java.io.*;

/**
 * Created by slin on 2019-06-29.
 */
public class ImageFrameWriter implements Runnable {

    private ImageFrame imageFrame;
    private File file;

    public ImageFrameWriter(ImageFrame imageFrame){
        this.imageFrame = imageFrame;
        file = new File("pic_" + System.currentTimeMillis() + ".jpg");
    }

    @Override
    public void run() {
        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(imageFrame.getImage().array());
        outputStream.flush();
        outputStream.close();
        LogUtils.info("write success: " + file.getName());
    }


}
