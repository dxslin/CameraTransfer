package com.slin.camera_transfer;

import com.slin.camera_transfer.utils.LogUtils;
import com.slin.camera_transfer.utils.Utils;
import com.slin.camera_transfer.writer.JpgImageFrameWriter;

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
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ImageFrameReceiver(Socket socket) throws IOException {
        this.mSocket = socket;
        inputStream = socket.getInputStream();
        //TODO 试下采用BufferedInputStream会不会快一些
//        inputStream = new BufferedInputStream(inputStream);
        outputStream = socket.getOutputStream();
        frameResolver = new ImageFrameResolver(inputStream);
    }


    @Override
    public void run() {
        try {
            startListen();
        } catch (IOException e) {
            LogUtils.info("disconnect: " + mSocket.getRemoteSocketAddress());
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
            JpgImageFrameWriter writer = new JpgImageFrameWriter(imageFrame);
            executorService.submit(writer);
        }
    }

}
