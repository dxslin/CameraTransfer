package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * author: slin
 * date: 2019-07-08
 * description:
 */
public abstract class ImageFrameWriter implements Writer {

    /**
     * 提供一个默认的执行器
     */
    public static Executor sDefaultExecutor = Executors.newSingleThreadExecutor();

    /**
     * 任务执行器
     */
    private Executor executor;

    public ImageFrameWriter() {
        executor = sDefaultExecutor;
    }

    public ImageFrameWriter(Executor executor) {
        this.executor = executor;
    }

    /**
     * 写数据
     *
     * @param frame
     * @throws Exception
     */
    protected abstract void writeImageFrame(ImageFrame frame) throws Exception;

    @Override
    public void write(ImageFrame frame) {
        executor.execute(() -> {
            try {
                writeImageFrame(frame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void destroy() {
        executor = null;
    }

    public Executor getExecutor() {
        return executor;
    }

}
