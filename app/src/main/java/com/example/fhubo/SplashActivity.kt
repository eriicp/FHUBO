package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // --- CÓDIGO DE LA ANIMACIÓN Y NAVEGACIÓN ---
        val logo = findViewById<ImageView>(R.id.iv_logo)

        // 1. Preparamos la animación: hacemos el logo invisible al principio
        logo.alpha = 0f

        // 2. Creamos la animación de fundido (fade in)
        logo.animate().apply {
            alpha(1f) // Lo hacemos totalmente opaco
            duration = 1500 // La animación durará 1.5 segundos
            withEndAction {
                // 3. Cuando la animación termine, esperamos un poco y navegamos
                lifecycleScope.launch {
                    delay(500) // Una pequeña pausa de 0.5 segundos
                    val intent = Intent(this@SplashActivity, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }
}
