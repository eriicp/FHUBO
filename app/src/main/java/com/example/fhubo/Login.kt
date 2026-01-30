package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setTheme(R.style.Theme_FHUBO)

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }
    }

    private fun handleLogin() {
        val email = binding.tietEmail.text.toString()
        val password = binding.tietPassword.text.toString()

        val emailError = viewModel.checkEmail(email)
        if (emailError != null) {
            binding.tilEmail.error = emailError
            return
        } else {
            binding.tilEmail.error = null
        }

        val isLoginSuccessful = viewModel.authenticate(email, password)

        if (isLoginSuccessful) {
            Toast.makeText(this, "Benvingut/da de nou!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "El correu o la contrasenya són incorrectes", Toast.LENGTH_LONG).show()
        }
    }
}
