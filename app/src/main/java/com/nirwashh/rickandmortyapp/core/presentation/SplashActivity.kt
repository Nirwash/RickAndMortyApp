package com.nirwashh.rickandmortyapp.core.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nirwashh.rickandmortyapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreen)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({startActivity(Intent(this, MainActivity::class.java))}, 3000)
    }
}