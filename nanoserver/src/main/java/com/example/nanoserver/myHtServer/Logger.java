package com.example.nanoserver.myHtServer;

import android.util.Log;

public class Logger {

    private static final String TAG = "MyHtServer";

    public static void i(String msg) {
        i(TAG,msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag,msg);
    }
}
