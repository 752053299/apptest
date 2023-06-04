package com.example.nanoserver.myHtServer;

import com.example.nanoserver.myHtServer.clentDispatch.AsyncRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听socket 的线程
 */
public class ServerRunnable implements Runnable{

    private ServerSocket serverSocket;
    private int timeOut;
    private int port;
    private boolean isBind;
    private IOException bindException;

    public ServerRunnable(ServerSocket serverSocket,int port, int timeOut) {
        this.serverSocket = serverSocket;
        this.timeOut = timeOut;
        this.port = port;
    }

    public boolean isBind() {
        return isBind;
    }

    public IOException getBindException() {
        return bindException;
    }

    @Override
    public void run() {
        try{
            serverSocket.bind(new InetSocketAddress(port));
            isBind = true;
            Logger.i("bind success!");
        }catch (IOException ioException) {
            bindException = ioException;
            return;
        }
        AsyncRunner asyncRunner = new AsyncRunner();
        do {
            try {
               Socket requestSocket = serverSocket.accept();
               asyncRunner.runAClient(new AsyncRunner.ClientHandler(asyncRunner,requestSocket));
            }catch (IOException ioException) {
                Logger.i("communicate broken");
                break;
            }
        }while (!serverSocket.isClosed());
        Logger.i("socket close");
    }
}
