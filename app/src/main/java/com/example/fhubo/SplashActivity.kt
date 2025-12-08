package com.example.fhubo

import android.content.Intent
import android.os.Bundle
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- CÓDIGO AÑADIDO CON COROUTINES ---
        // Lanza una coroutine asociada al ciclo de vida de la actividad
        lifecycleScope.launch {
            // Espera 2000 milisegundos (2 segundos) de forma no bloqueante
            delay(2000)

            // Crea el Intent para ir a la LoginActivity
            val intent = Intent(this@SplashActivity, Login::class.java)
            startActivity(intent)

            // Cierra la SplashActivity
            finish()
        }
        // --- FIN DEL CÓDIGO AÑADIDO ---
    }
}

