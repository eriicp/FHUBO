package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton

class Popup2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se elimina 'enableEdgeToEdge()' y el listener de insets, no son para popups.
        setContentView(R.layout.activity_popup2)

        // --- Inicialización de vistas ---
        // Asegúrate de que tu 'activity_popup2.xml' tiene botones con estos IDs
        val btnNext = findViewById<MaterialButton>(R.id.btn_next)
        val background = findViewById<ConstraintLayout>(R.id.popup2_background)

        val btn_skip = findViewById<MaterialButton>(R.id.btn_skip)

        btn_skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // Acción para el botón "Següent"
        btnNext.setOnClickListener {
            // Abre el siguiente popup (Popup3)
            val intent = Intent(this, Popup3::class.java)
            startActivity(intent)
            // Cierra el popup actual para no poder volver con el botón de atrás del sistema
            finish()
        }

        // Acción para el botón "Saltar"


        // Si el usuario pulsa en el fondo oscuro, también se cierra.
        background.setOnClickListener {
            finish()
        }
    }
}
