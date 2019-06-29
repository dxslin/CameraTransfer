package com.slin.camera_transfer.transfer.writer;

import com.slin.camera_transfer.model.ImageFrame;

import java.io.IOException;

/**
 * author: slin
 * date: 2019-06-29
 * description:
 */
public interface ImageFrameWriter {

    void write() throws IOException ;


    ImageFrame getImageFrame();

}
