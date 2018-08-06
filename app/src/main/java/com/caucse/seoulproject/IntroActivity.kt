package com.caucse.seoulproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.caucse.seoulproject.controller.ApiController

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initApplication()
        val handler = Handler()
        handler.postDelayed(Runnable {
            //startActivity(Intent(this, LoginActivity::class.java))
            startActivity(Intent(this, ListActivity::class.java))
        }, 2000)
    }

    fun initApplication() {
        ApiController.context = application
    }
}
