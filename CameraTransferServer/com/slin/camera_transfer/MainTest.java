package com.slin.camera_transfer;

import java.io.IOException;

/**
 * Created by slin on 2019-06-29.
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        CameraTransferServer cameraTransferServer = new CameraTransferServer(4040);
        cameraTransferServer.start();
        cameraTransferServer.join();
    }

}
