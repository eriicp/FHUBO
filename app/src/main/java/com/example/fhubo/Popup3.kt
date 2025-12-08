package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class Popup3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popup3)
        val btnBack = findViewById<MaterialButton>(R.id.btn_back)
        val btnNext = findViewById<MaterialButton>(R.id.btn_next)
        val btn_skip = findViewById<MaterialButton>(R.id.btn_skip)

        btn_skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            // Vuelve al popup anterior (Popup2)
            val intent = Intent(this, Popup2::class.java)
            startActivity(intent)
            finish()
        }

        btnNext.setOnClickListener {
            // Avanza al siguiente popup (Popup4)
            val intent = Intent(this, Popup4::class.java)
            startActivity(intent)
            finish()
        }
    }
}