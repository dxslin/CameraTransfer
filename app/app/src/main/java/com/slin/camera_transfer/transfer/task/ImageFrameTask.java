package com.slin.camera_transfer.transfer.task;

import com.slin.camera_transfer.model.ImageFrame;

import java.io.IOError;
import java.io.IOException;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public interface ImageFrameTask {

    void run() throws IOException;

    ImageFrame getImageFrame();

}
