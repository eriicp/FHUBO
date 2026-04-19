package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fhubo.Main.MainActivity
import com.google.android.material.button.MaterialButton

class Popup2 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup2)

        val btnNext = findViewById<MaterialButton>(R.id.btn_next)
        val background = findViewById<ConstraintLayout>(R.id.popup2_background)

        val btn_skip = findViewById<MaterialButton>(R.id.btn_skip)

        btn_skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        btnNext.setOnClickListener {
            val intent = Intent(this, Popup3::class.java)
            startActivity(intent)
            finish()
        }

        background.setOnClickListener {
            finish()
        }
    }
}
