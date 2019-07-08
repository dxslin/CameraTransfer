package com.slin.camera_transfer;

import com.slin.camera_transfer.writer.JpgImageFrameWriter;

/**
 * Created by slin on 2019-06-29.
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        CameraTransfer cameraTransfer = new CameraTransfer(4040);
        cameraTransfer.registerWriter(new JpgImageFrameWriter());
//        cameraTransfer.registerWriter(new BmpImageFrameWriter(Executors.newFixedThreadPool(1)));
        cameraTransfer.start();
        cameraTransfer.getCameraTransferServer().join();
    }

}
