package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.databinding.ActivitySigninBinding // 1. Importar ViewBinding

class Signin : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val name = binding.tietUsername.text.toString()
            val email = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()
            val confirmPassword = binding.tietConfirmPassword.text.toString()

            val errorMessage = viewModel.register(name, password, confirmPassword, email)

            if (errorMessage == null) {
                Toast.makeText(this, "Usuari '$name' registrat correctament!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
            }
        }

        binding.tvNoLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvGoToLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}