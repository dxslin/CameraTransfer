package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class CameraTransferServer extends Thread {


    private int mPort;

    private ServerSocket serverSocket;

    private boolean running = false;
    private ExecutorService executorService = Executors.newCachedThreadPool();


    public CameraTransferServer(int port){
        this.mPort = port;
    }

    private void startServer() throws IOException {
        LogUtils.info("开始监听：" + mPort);
        serverSocket = new ServerSocket(mPort);
        running = true;
        while (running) {
            //等待连接
            Socket socket = serverSocket.accept();
            //线程池启动数据接收
            executorService.submit(new ImageFrameReceiver(socket));
        }
        if(!serverSocket.isClosed()) {
            serverSocket.close();
        }
        LogUtils.info("结束监听：" + mPort);
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
        executorService.shutdown();
    }

    public boolean isRunning() {
        return running;
    }


}
