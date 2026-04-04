package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.fhubo.Main.MainActivity
import com.google.android.material.button.MaterialButton

class Popup5 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popup5)
        val btnBack = findViewById<MaterialButton>(R.id.btn_back)
        val btnFinish = findViewById<MaterialButton>(R.id.btn_finish)

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, Popup4::class.java)
            startActivity(intent)
            finish()
        }
    }
}
