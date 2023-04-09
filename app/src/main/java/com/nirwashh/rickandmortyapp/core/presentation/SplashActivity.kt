package com.nirwashh.rickandmortyapp.core.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.nirwashh.rickandmortyapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ startActivity(Intent(this, MainActivity::class.java)) }, 2500)
        Handler().postDelayed({ finish() }, 2500)
    }
}