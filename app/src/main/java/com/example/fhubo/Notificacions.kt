package com.example.fhubo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class Notificacions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout para esta actividad
        setContentView(R.layout.activity_notificacions)

        // Encuentra la Toolbar por su ID
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_notifications)

        // Asigna una acción al icono de navegación (la flecha de atrás)
        toolbar.setNavigationOnClickListener {
            // Cierra la actividad actual y vuelve a la anterior
            finish()
        }
    }
}
