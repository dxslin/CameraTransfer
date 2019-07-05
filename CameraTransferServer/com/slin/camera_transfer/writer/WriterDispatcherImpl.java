package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: slin
 * date: 2019-07-01
 * description:
 */
public class WriterDispatcherImpl implements WriterDispatcher {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private List<Writer.Factory> registeredWriterList = new ArrayList<>();

    @Override
    public void registerWriter(Writer.Factory factory) {
        registeredWriterList.add(factory);
    }

    @Override
    public void write(ImageFrame imageFrame) {
        for (Writer.Factory factory : registeredWriterList) {
            Writer writer = createWriter(factory, imageFrame);
            if (writer != null) {
                executorService.execute(writer);
            }
        }
    }

    /**
     * 创建writer
     * 优化：应该可以创建一个writer池，将回收了的writer重复利用
     *
     * @param factory    factory
     * @param imageFrame parameter imageFrame
     * @return Writer
     */
    private Writer createWriter(Writer.Factory factory, ImageFrame imageFrame) {
        return factory.createWriter(imageFrame);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
        registeredWriterList.clear();
    }
}
