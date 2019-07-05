package com.slin.camera_transfer;

import com.slin.camera_transfer.writer.JpgWriter;
import com.slin.camera_transfer.writer.Writer;

/**
 * Created by slin on 2019-06-29.
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        CameraTransfer cameraTransfer = new CameraTransfer(4040);
        cameraTransfer.registerWriter(new Writer.Factory<>(JpgWriter.class));
//        cameraTransfer.registerWriter(new Writer.Factory<>(BmpWriter.class));
        cameraTransfer.start();
        cameraTransfer.getCameraTransferServer().join();
    }

}
