package com.example.fhubo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class Language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_language)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
