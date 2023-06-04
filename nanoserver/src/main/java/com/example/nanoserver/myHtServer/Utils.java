package com.example.nanoserver.myHtServer;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Utils {


    public static void safeClose(Object closeable) {
        if (closeable == null) return;
        try{
            if (closeable instanceof Socket) {
                ((Socket)closeable).close();
            }else if (closeable instanceof ServerSocket){
                ((ServerSocket) closeable).close();
            }else if (closeable instanceof Closeable) {
                ((Closeable) closeable).close();
            }else {
                throw new IllegalArgumentException("nothing to close");
            }
        }catch (IOException e){
            e.printStackTrace();
            Logger.i("Could not close: " + e.getMessage());
        }
    }
}
