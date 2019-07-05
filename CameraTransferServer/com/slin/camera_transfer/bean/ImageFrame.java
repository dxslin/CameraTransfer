package com.slin.camera_transfer.bean;

import java.nio.ByteBuffer;

/**
 * Created by slin on 2019-06-29.
 */
public class ImageFrame {

    public static final String TITLE_TAG = "camera_transfer";
    public static final int TITLE_TAG_SIZE = TITLE_TAG.length();

    private int width;
    private int height;
    private int length;
    private ByteBuffer image;

    public ImageFrame() {
    }

    public ImageFrame(int width, int height, ByteBuffer image) {
        this.width = width;
        this.height = height;
        this.image = image;
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

    public ByteBuffer getImage() {
        return image;
    }

    public void setImage(ByteBuffer image) {
        this.image = image;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
