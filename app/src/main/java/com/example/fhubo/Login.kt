package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fhubo.Main.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    // Declaració de vistes
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tilEmail: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setTheme(R.style.Theme_FHUBO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicialitzem les vistes amb findViewById
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
        tietEmail = findViewById(R.id.tietEmail)
        tietPassword = findViewById(R.id.tietPassword)
        tilEmail = findViewById(R.id.tilEmail)

        setupListeners()
        setupObservers() // <-- Nueva función para observar al ViewModel
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            val email = tietEmail.text.toString()
            val password = tietPassword.text.toString()
            // Simplemente le pasamos los datos al ViewModel, sin esperar 'return'
            viewModel.login(email, password)
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        // Observar errores de email
        viewModel.emailError.observe(this) { error ->
            tilEmail.error = error
        }

        // Observar errores generales de login
        viewModel.loginError.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }

        // Observar si el login fue un éxito
        viewModel.loginSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Benvingut/da de nou!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}
