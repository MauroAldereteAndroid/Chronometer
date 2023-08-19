package com.maurodev.chronometer.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.maurodev.chronometer.R
import com.maurodev.chronometer.ui.principal.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        /**
         * This screen is shown during the added time.
         * Once the time is over, you are redirected to the activity declared in the intent.
         */
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_SHOW)
    }

    companion object {
        private const val TIME_SHOW = 3000L
    }
}