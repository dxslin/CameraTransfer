package com.slin.camera_transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * author: slin
 * date: 2019-06-27
 * description:
 */
public class SocketTransfer {

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    private String mServerIp;
    private int mPort;

    public SocketTransfer(String serverIp, int port){
        this.mServerIp = serverIp;
        this.mPort = port;
    }

    public void connect() throws IOException {
        socket = new Socket(mServerIp, mPort);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }



}
