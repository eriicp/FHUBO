package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.fhubo.Main.MainActivity
import com.google.android.material.button.MaterialButton

class Popup4 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popup4)
        val btnBack = findViewById<MaterialButton>(R.id.btn_back)
        val btnNext = findViewById<MaterialButton>(R.id.btn_next)
        val btn_skip = findViewById<MaterialButton>(R.id.btn_skip)

        btn_skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, Popup3::class.java)
            startActivity(intent)
            finish()
        }

        btnNext.setOnClickListener {
            val intent = Intent(this, Popup5::class.java)
            startActivity(intent)
            finish()
        }
    }
}
