package com.slin.camera_transfer.transfer.task;

import com.slin.camera_transfer.model.ImageFrame;
import com.slin.camera_transfer.transfer.writer.ImageFrameWriter;

import java.io.IOException;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public class ImageFrameTaskImpl implements ImageFrameTask {

    private ImageFrameWriter imageFrameWriter;

    public ImageFrameTaskImpl(ImageFrameWriter writer) {
        this.imageFrameWriter = writer;
    }


    @Override
    public void run() throws IOException {
        imageFrameWriter.write();
    }

    @Override
    public ImageFrame getImageFrame() {
        return imageFrameWriter.getImageFrame();
    }
}
