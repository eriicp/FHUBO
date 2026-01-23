package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fhubo.Main.MainActivity

class Signin : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signinButton = findViewById<Button>(R.id.btnRegister)
        val skipLoginButton = findViewById<TextView>(R.id.tvNoLogin)
        val loginButton = findViewById<TextView>(R.id.tvGoToLogin)
        val nomUsuariEditText = findViewById<EditText>(R.id.tietUsername)
        val emailEditText = findViewById<EditText>(R.id.tietEmail)
        val passwordEditText = findViewById<EditText>(R.id.tietPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.tietConfirmPassword)



        signinButton.setOnClickListener {
            val t1 = nomUsuariEditText.text.toString()
            val t2 = passwordEditText.text.toString()

            viewModel.register(t1, t2)

            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        skipLoginButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}
