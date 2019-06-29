package com.slin.camera_transfer.model;

import android.media.Image;
import android.media.ImageReader;

import java.nio.ByteBuffer;

/**
 * Created by slin on 2019-06-29.
 */
public class ImageFrame {

    public static final String TITLE_TAG = "camera_transfer";
    public static final int TITLE_TAG_SIZE = TITLE_TAG.length();

    private byte[] titleTag = TITLE_TAG.getBytes();
    private int width;
    private int height;
    private int length;
    private ByteBuffer imageBuffer;

    public ImageFrame() {
    }

    public ImageFrame(Image image){
        this.width = image.getWidth();
        this.height = image.getHeight();
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        length = buffer.remaining();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        imageBuffer = ByteBuffer.wrap(bytes);
        image.close();
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ByteBuffer getImageBuffer() {
        return imageBuffer;
    }

    public void setImageBuffer(ByteBuffer imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(byte[] titleTag) {
        this.titleTag = titleTag;
    }

    @Override
    public String toString() {
        return "ImageFrame{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                '}';
    }
}
