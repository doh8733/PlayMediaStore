package com.example.playmediastore

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    private val btnPlay: Button by lazy { findViewById<Button>(R.id.btn_play) }
    private val btnPause: Button by lazy { findViewById<Button>(R.id.btn_pause) }
    private val btnSwitch: Button by lazy { findViewById<Button>(R.id.btn_switch) }
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var audioManager: AudioManager
    private var isPlayOnSpeaker: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playNow()
        btnPlay.setOnClickListener {
            play()
        }
        btnSwitch.setOnClickListener {
            switch()
        }
    }

//    fun init(){
//        mediaPlayer = MediaPlayer.create(this, R.raw.song)
//        val context = this.baseContext
//        audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager
//        audioManager.mode = AudioManager.MODE_IN_CALL
//        isPlayOnSpeaker = true
//
//    }
    private fun play() {
        mediaPlayer.start()
    }

    private fun pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause()
        }
    }
    private fun switch() {
        if (!isPlayOnSpeaker){
            isPlayOnSpeaker = true
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.isSpeakerphoneOn = true
        }
        else{
            isPlayOnSpeaker = false
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.isSpeakerphoneOn = false
        }
    }
    fun playNow(){
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        mediaPlayer.start()
        val context = this.baseContext
        isPlayOnSpeaker = true
        audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager
        audioManager.mode = AudioManager.MODE_IN_CALL
        audioManager.isSpeakerphoneOn = false
        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),0)

    }
}