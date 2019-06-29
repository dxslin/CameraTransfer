package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;
import com.slin.camera_transfer.utils.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by slin on 2019-06-29.
 */
public class ImageFrameReceiver implements Runnable {

    private Socket mSocket;

    private ImageFrameResolver frameResolver;

    private InputStream inputStream;
    private OutputStream outputStream;

    //这里应该创建一个receiverManager和writerManager管理receiver和writer，所有的receiver和writer都提交到manager里面去，时间不允许了
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ImageFrameReceiver(Socket socket) throws IOException {
        this.mSocket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        frameResolver = new ImageFrameResolver(inputStream);
    }


    @Override
    public void run() {
        try {
            startListen();
        } catch (IOException e) {
            e.printStackTrace();
            Utils.close(inputStream);
            Utils.close(outputStream);
            Utils.close(mSocket);
        }
        executorService.shutdown();
    }

    public void writeData(byte[] data) throws IOException {
        outputStream.write(data);
    }

    private void startListen() throws IOException {
        while (frameResolver.checkTitle()){
            ImageFrame imageFrame = frameResolver.resolve();
            LogUtils.info("resolved: " + imageFrame.toString());
            ImageFrameWriter writer = new ImageFrameWriter(imageFrame);
            executorService.submit(writer);
        }
    }

}
