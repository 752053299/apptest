package com.example.nanoserver.myHtServer;


import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 */
public abstract class MyHttpServer {
    //private String ip;

    private int port;
    private Thread serverThread;
    private ServerSocket serverSocket;
    private ServerSocketFactory serverSocketFactory = new DefaultServerSocket();

    private ServerRunnable serverRunnable;

    private class DefaultServerSocket implements ServerSocketFactory {
        @Override
        public ServerSocket create() throws IOException {
            return new ServerSocket();
        }
    }

    public MyHttpServer(int port) {
        this.port = port;
    }


    public void setServerSocketFactory(ServerSocketFactory serverSocketFactory) {
        this.serverSocketFactory = serverSocketFactory;
    }

    public ServerSocketFactory getServerSocketFactory() {
        return serverSocketFactory;
    }


    public void start(final int timeout, boolean daemon) throws IOException  {
        serverSocket = getServerSocketFactory().create();
        serverRunnable = new ServerRunnable(serverSocket,port,timeout);
        serverThread = new Thread(serverRunnable);
        serverThread.setName("MyHttpServer");
        serverThread.setDaemon(daemon);
        serverThread.start();
        while (!serverRunnable.isBind() && serverRunnable.getBindException() == null) {
            try {
                Thread.sleep(10);
            }catch (Exception ignore) {

            }
        }
    }

    public void stop() {
        Utils.safeClose(serverSocket);

    }


}
