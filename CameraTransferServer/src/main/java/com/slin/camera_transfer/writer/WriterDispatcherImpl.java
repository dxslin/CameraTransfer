package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * author: slin
 * date: 2019-07-01
 * description:
 */
public class WriterDispatcherImpl implements WriterDispatcher {


    private List<Writer> registeredWriterList = new ArrayList<>();

    public WriterDispatcherImpl() {

    }

    @Override
    public void registerWriter(Writer writer) {
        registeredWriterList.add(writer);
    }

    @Override
    public void write(ImageFrame imageFrame) {
        for (Writer writer : registeredWriterList) {
            writer.write(imageFrame);
        }
    }

    @Override
    public void destroy() {
        for (Writer writer : registeredWriterList) {
            writer.destroy();
        }
        registeredWriterList.clear();
    }
}
