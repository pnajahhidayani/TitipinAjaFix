package com.example.titipinajamyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay the transition to GetStartedActivity
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // Delay selama 2 detik

    }
}