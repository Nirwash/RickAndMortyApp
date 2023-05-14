package com.nirwashh.rickandmortyapp.core.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.nirwashh.rickandmortyapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Handler().postDelayed({ startActivity(Intent(this, MainActivity::class.java)) }, 2000)
        Handler().postDelayed({ finish() }, 2000)
    }
}