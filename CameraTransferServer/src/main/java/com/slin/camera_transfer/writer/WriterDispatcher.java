package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

/**
 * author: slin
 * date: 2019-07-01
 * description:
 */
public interface WriterDispatcher {

    void registerWriter(Writer writer);

    void write(ImageFrame imageFrame);

    void destroy();

}
