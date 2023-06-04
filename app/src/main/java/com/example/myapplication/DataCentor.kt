package com.example.myapplication

import android.util.Log


object DataCenter {

    private var callbacklist: MutableList<TestCallback> = mutableListOf()

    fun addTestCallback(testCallback: TestCallback) {
        this.callbacklist.add(testCallback)
    }
    init {
        val runnab = Runnable {
            while (true) {
                Thread.sleep(3000)
                Log.i("Data", "my callback: ${callbacklist.size}")
            }
        }
        val thread = Thread(runnab)
        thread.start()
    }
}

interface TestCallback {
    fun onDataChange()
}