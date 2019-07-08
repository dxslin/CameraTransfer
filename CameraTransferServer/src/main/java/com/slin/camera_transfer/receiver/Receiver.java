package com.slin.camera_transfer.receiver;

import com.slin.camera_transfer.bean.ImageFrame;
import com.slin.camera_transfer.resolver.ImageFrameResolver;
import com.slin.camera_transfer.utils.LogUtils;
import com.slin.camera_transfer.utils.Utils;
import com.slin.camera_transfer.writer.WriterDispatcher;

import java.io.*;
import java.net.Socket;

/**
 * Created by slin on 2019-06-29.
 */
public class Receiver implements Runnable {

    private Socket mSocket;
    private WriterDispatcher mWriterDispatcher;

    private ImageFrameResolver frameResolver;

    private InputStream inputStream;
    private OutputStream outputStream;

    public Receiver(Socket socket, WriterDispatcher dispatcher) throws IOException {
        this.mSocket = socket;
        this.mWriterDispatcher = dispatcher;
        inputStream = new BufferedInputStream(socket.getInputStream());
        outputStream = new BufferedOutputStream(socket.getOutputStream());
        frameResolver = new ImageFrameResolver(inputStream);
    }


    @Override
    public void run() {
        try {
            startListen();
        } catch (IOException e) {
            LogUtils.i("disconnect: " + mSocket.getRemoteSocketAddress());
            e.printStackTrace();
            Utils.close(inputStream);
            Utils.close(outputStream);
            Utils.close(mSocket);
        }
    }

    public void writeData(byte[] data) throws IOException {
        outputStream.write(data);
    }

    private void startListen() throws IOException {
        while (frameResolver.checkTitle()){
            ImageFrame imageFrame = frameResolver.resolve();
            LogUtils.i("resolved: " + imageFrame.toString());
            mWriterDispatcher.write(imageFrame);
        }
    }

}
