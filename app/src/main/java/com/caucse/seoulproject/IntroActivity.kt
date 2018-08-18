package com.caucse.seoulproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val handler = Handler()
        handler.postDelayed(Runnable {
            //startActivity(Intent(this, LoginActivity::class.java))
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}
