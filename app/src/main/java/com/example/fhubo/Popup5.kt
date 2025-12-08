package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class Popup5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popup5)
        val btnBack = findViewById<MaterialButton>(R.id.btn_back)
        val btnFinish = findViewById<MaterialButton>(R.id.btn_finish) // Asumiendo que el botón final tiene este ID

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnBack.setOnClickListener {
            // Vuelve al popup anterior (Popup4)
            val intent = Intent(this, Popup4::class.java)
            startActivity(intent)
            finish()
        }
    }
}