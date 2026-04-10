package com.example.fhubo.Settings

import android.os.Bundle
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import com.google.android.material.appbar.MaterialToolbar

class Language : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_language)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
