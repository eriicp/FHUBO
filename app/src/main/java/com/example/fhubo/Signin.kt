package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fhubo.Main.MainActivity

class Signin : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)

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
            val t3 = confirmPasswordEditText.text.toString()
            val t4 = emailEditText.text.toString()


            val register = viewModel.register(t1, t2, t3, t4)

            if (register == null) {
                Toast.makeText(
                    this,
                    "Usuari registrat: ${viewModel.usersList.get(viewModel.usersList.size - 1).name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(
                    this,
                    "Error: ${register}",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


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
