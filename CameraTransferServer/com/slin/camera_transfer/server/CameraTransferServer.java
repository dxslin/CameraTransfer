package com.slin.camera_transfer.server;

import com.slin.camera_transfer.receiver.Receiver;
import com.slin.camera_transfer.receiver.ReceiverManager;
import com.slin.camera_transfer.utils.LogUtils;
import com.slin.camera_transfer.writer.WriterDispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CameraTransferServer extends Thread {

    private int mPort;
    private ReceiverManager mReceiverManager;
    private WriterDispatcher mWriterDispatcher;

    private ServerSocket serverSocket;

    private boolean running = false;

    public CameraTransferServer(int port, ReceiverManager receiverManager, WriterDispatcher dispatcher) {
        this.mPort = port;
        this.mReceiverManager = receiverManager;
        this.mWriterDispatcher = dispatcher;
    }

    private void startServer() throws IOException {
        LogUtils.i("开始监听：" + mPort);
        serverSocket = new ServerSocket(mPort);
        running = true;
        while (running) {
            //等待连接
            Socket socket = serverSocket.accept();
            LogUtils.i("connected：" + socket.getRemoteSocketAddress());
            //线程池启动数据接收
            mReceiverManager.post(new Receiver(socket, mWriterDispatcher));

        }
        if(!serverSocket.isClosed()) {
            serverSocket.close();
        }
        LogUtils.i("结束监听：" + mPort);
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destory(){
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mReceiverManager.destroy();
    }

    public boolean isRunning() {
        return running;
    }


}
