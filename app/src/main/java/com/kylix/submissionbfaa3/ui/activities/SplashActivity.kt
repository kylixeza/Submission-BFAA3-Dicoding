package com.kylix.submissionbfaa3.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    companion object {
        const val TIME_FOR_SPLASH = 3000L
    }
    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        handler = Handler(mainLooper)
        progress.start()
        progress.loadingColor = R.color.colorAccent
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            progress.stop()
            finish()
        }, TIME_FOR_SPLASH)
    }
}