package com.slin.camera_transfer;

import com.slin.camera_transfer.receiver.ReceiverManager;
import com.slin.camera_transfer.receiver.ReceiverManagerImpl;
import com.slin.camera_transfer.server.CameraTransferServer;
import com.slin.camera_transfer.writer.Writer;
import com.slin.camera_transfer.writer.WriterDispatcher;
import com.slin.camera_transfer.writer.WriterDispatcherImpl;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public class CameraTransfer {

    private ReceiverManager receiverManager;
    private WriterDispatcher writerDispatcher;
    private CameraTransferServer cameraTransferServer;

    public CameraTransfer(int port) {
        receiverManager = new ReceiverManagerImpl();
        writerDispatcher = new WriterDispatcherImpl();
        cameraTransferServer = new CameraTransferServer(port, receiverManager, writerDispatcher);
    }

    public void start() {
        cameraTransferServer.start();
    }

    public CameraTransferServer getCameraTransferServer() {
        return cameraTransferServer;
    }

    public void registerWriter(Writer.Factory factory) {
        writerDispatcher.registerWriter(factory);
    }

}
