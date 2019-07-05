package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

/**
 * author: slin
 * date: 2019-07-01
 * description:
 */
public interface WriterDispatcher {

    void registerWriter(Writer.Factory factory);

    void write(ImageFrame imageFrame);

}
