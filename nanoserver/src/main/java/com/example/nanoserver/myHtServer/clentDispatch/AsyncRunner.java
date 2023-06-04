package com.example.nanoserver.myHtServer.clentDispatch;

import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 一个socket链接维护一个AsyncRunner
 *
 */
public class AsyncRunner implements IAsyncRunner{



    private ThreadFactory socketThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("Request Processor ( "+ runningClient.size() + ")");
            return thread;
        }
    };

    private Executor executor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,socketTimeOut, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),socketThreadFactory);

    private ArrayDeque<ClientHandler> runningClient = new ArrayDeque<>();


    @Override
    public void closeAll() {
        for (ClientHandler clientHandler : runningClient) {
            clientHandler.close();
        }
    }



    @Override
    public void closeClient(ClientHandler clientHandler) {
        runningClient.remove(clientHandler);
    }

    @Override
    public void runAClient(ClientHandler clientHandler) {
        executor.execute(clientHandler);
        runningClient.add(clientHandler);
    }

    /**
     * ClientHandler
     * 通过socketTimeOut 来限定最长读数据等待时间，超过此时间没有请求就断开链接。
     */
    public static class ClientHandler implements Runnable{
        private int socketTimeOut = 60;
        private Socket socketAccept;
        private AsyncRunner runner;
        public ClientHandler(AsyncRunner runner,Socket socketAccept) {
            this.socketAccept = socketAccept;
            this.runner = runner;
        }

        @Override
        public void run() {
            try {
                socketAccept.setSoTimeout(60);
                InputStream inputStream = socketAccept.getInputStream();

            }catch (Exception e){

            }
        }

        public void close() {
            runner.closeClient(this);
        }
    }
}
