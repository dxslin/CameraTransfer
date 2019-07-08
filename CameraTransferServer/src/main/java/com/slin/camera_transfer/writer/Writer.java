package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

/**
 * author: slin
 * date: 2019-07-08
 * description:
 */
public interface Writer {

    void write(ImageFrame frame);

    void destroy();
}
