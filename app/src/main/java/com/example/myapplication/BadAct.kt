package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class BadAct :AppCompatActivity() {

    private var largeObj : ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bad_layout)
        createLargObj()
    }

    private fun createLargObj() {
      Thread.sleep(1000)
    }
}