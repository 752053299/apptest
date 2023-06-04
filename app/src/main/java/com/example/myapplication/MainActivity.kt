package com.example.myapplication

import android.content.Intent
import android.media.AudioManager.STREAM_MUSIC
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.Engine.KEY_PARAM_STREAM
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.player.Dispat


class MainActivity : AppCompatActivity(){
    private val TAG = "Dispat"
    private var testBt: Button? = null
    private var dispat:Dispat = Dispat()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        testBt = findViewById(R.id.test_btn)
        dispat.init()
        testBt?.setOnClickListener {
            val audio = dispat.takeAudioPack()
            Log.i(TAG, "audio: $audio")
            dispat.takeVideoPack()
        }
    }






    override fun onDestroy() {
        super.onDestroy()
    }



}