package com.example.fhubo.Settings

import android.os.Bundle
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import com.google.android.material.appbar.MaterialToolbar

class Notificacions : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacions)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_notifications)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
