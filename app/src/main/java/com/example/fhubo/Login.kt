package com.example.fhubo

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fhubo.Main.MainActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Instalar la Splash Screen y guardar la instancia
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Añadimos la animación de salida (fade-out)
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            // Creamos un fade-out para la vista entera de la splash screen
            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenViewProvider.view,
                View.ALPHA,
                1f,
                0f
            )
            fadeOut.interpolator = DecelerateInterpolator()
            fadeOut.duration = 500L // Duración de 0.5 segundos

            // Le decimos que elimine la vista de la splash screen cuando la animación termine
            fadeOut.doOnEnd {
                splashScreenViewProvider.remove()
            }

            // Iniciamos la animación
            fadeOut.start()
        }


        // --- El resto de tu código permanece igual ---
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<TextView>(R.id.tvRegister)
        val skipLoginButton = findViewById<TextView>(R.id.tvNoLogin)
        loginButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            intent = Intent(this, Signin::class.java)
            startActivity(intent)
            finish()
        }
        skipLoginButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}