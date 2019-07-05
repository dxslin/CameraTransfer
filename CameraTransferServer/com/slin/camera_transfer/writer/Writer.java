package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;
import com.slin.camera_transfer.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public abstract class Writer implements Runnable {

    private ImageFrame mImageFrame;

    public Writer(ImageFrame frame) {
        this.mImageFrame = frame;
    }

    public ImageFrame getImageFrame() {
        return mImageFrame;
    }

    public void run() {
        try {
            write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void write() throws Exception;

    public static class Factory<T extends Writer> {

        private Class<T> tClass;

        public Factory(Class<T> tClass) {
            this.tClass = tClass;
        }

        public Writer createWriter(ImageFrame frame) {
            return create(frame);
        }

        private Writer create(ImageFrame frame) {
            try {
                Constructor<T> constructor = tClass.getConstructor(ImageFrame.class);
                return constructor.newInstance(frame);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                LogUtils.e("cannot create the " + tClass.getSimpleName());
                e.printStackTrace();
            }
            return null;
        }

    }

}
