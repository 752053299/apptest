package com.example.myapplication.player;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dispat {
    private static final String TAG = "Dispat";

    private int readsize = 0;
    private Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition tackPackage = lock.newCondition();
    private PackageDisp videoP = new PackageDisp();
    private PackageDisp audioP = new PackageDisp();

    public void init(){
        videoP.init("videoP");
        audioP.init("audioP");
        Thread thread = new Thread(new ReadThread());
        thread.start();
    }

    public String takeVideoPack() {
        return videoP.takePackage();
    }

    public String takeAudioPack() {
        return audioP.takePackage();
    }

    private void checkFullAndWait() {
        try {
            lock.lockInterruptibly();
            while (readsize == 50){
                Log.i(TAG, "read max size");
                tackPackage.await();
            }
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }



    private boolean readThreadRun = true;
    private class ReadThread implements Runnable {
        @Override
        public void run() {
            int testP = 0;
            while (readThreadRun) {
                checkFullAndWait();
                String pack = FFPackage.INSTANCE.getpackage();
                readsize++;
                testP++;

                Log.i(TAG, "read pack: " + testP);

                if (testP == 1){
                    videoP.addPackage(pack);
                }else {
                    audioP.addPackage(pack);
                }
                if (testP == 11){
                    testP = 0;
                }
            }
        }
    }

    private class PackageDisp {
        private HandlerThread handlerThread;
        private MsgHandler msgHandler;
        private BlockingQueue<String> packQueue = new ArrayBlockingQueue<>(50);

        public void init(String name) {
            handlerThread = new HandlerThread(name);
            handlerThread.start();
            msgHandler = new MsgHandler(handlerThread.getLooper());
        }

        private class MsgHandler extends Handler{

            public MsgHandler(Looper looper) {
                super(looper);
            }
            @Override
            public void handleMessage(@NonNull Message msg) {
                String ms = (String) msg.obj;
                try {
                    packQueue.put(ms);
                } catch (InterruptedException e) {

                }
            }
        }


        public String takePackage() {
            String pack = "";
            try {
                pack = packQueue.take();
                tackPackage.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return pack;
        }


        public void addPackage(String pack) {
            Message ma  = Message.obtain();
            ma.obj = pack;
            msgHandler.sendMessage(ma);
        }

    }

}
