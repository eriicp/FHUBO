package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.iv_logo)

        logo.alpha = 0f

        logo.animate().apply {
            alpha(1f)
            duration = 1500
            withEndAction {
                lifecycleScope.launch {
                    delay(500)
                    val intent = Intent(this@SplashActivity, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }
}
