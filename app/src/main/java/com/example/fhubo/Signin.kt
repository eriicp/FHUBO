package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fhubo.Main.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Signin : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    // Declaració de vistes
    private lateinit var btnRegister: Button
    private lateinit var tvNoLogin: TextView
    private lateinit var tvGoToLogin: TextView
    private lateinit var tietUsername: TextInputEditText
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tietConfirmPassword: TextInputEditText
    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Inicialitzem les vistes amb findViewById
        btnRegister = findViewById(R.id.btnRegister)
        tvNoLogin = findViewById(R.id.tvNoLogin)
        tvGoToLogin = findViewById(R.id.tvGoToLogin)
        tietUsername = findViewById(R.id.tietUsername)
        tietEmail = findViewById(R.id.tietEmail)
        tietPassword = findViewById(R.id.tietPassword)
        tietConfirmPassword = findViewById(R.id.tietConfirmPassword)
        tilUsername = findViewById(R.id.tilUsername)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)

        setupListeners()
        setupObservers() // Configuramos los observadores del ViewModel
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            val name = tietUsername.text.toString()
            val email = tietEmail.text.toString()
            val password = tietPassword.text.toString()
            val confirmPassword = tietConfirmPassword.text.toString()

            // Pasamos la responsabilidad al ViewModel
            viewModel.register(name, password, confirmPassword, email)
        }

        tvNoLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvGoToLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupObservers() {
        // Observamos cada posible error y lo inyectamos en su vista correspondiente
        viewModel.usernameError.observe(this) { error ->
            tilUsername.error = error
        }

        viewModel.emailError.observe(this) { error ->
            tilEmail.error = error
        }

        viewModel.passwordError.observe(this) { error ->
            tilPassword.error = error
        }

        viewModel.confirmPasswordError.observe(this) { error ->
            tilConfirmPassword.error = error
        }

        // Observamos el éxito del registro
        viewModel.registerSuccess.observe(this) { success ->
            if (success) {
                val name = tietUsername.text.toString()
                Toast.makeText(this, "Usuari '$name' registrat correctament!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}