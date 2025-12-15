package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // No es necesario 'enableEdgeToEdge' si usas una AppBarLayout que ya gestiona los insets.
        setContentView(R.layout.activity_settings)

        // --- INICIALIZACIÓN DE VISTAS ---
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_language)
        val llUser = findViewById<LinearLayout>(R.id.ll_user)
        val llPremium = findViewById<LinearLayout>(R.id.ll_premium)
        val llLanguage = findViewById<LinearLayout>(R.id.ll_language)
        val llNotifications = findViewById<LinearLayout>(R.id.ll_notifications)
        val llHelp = findViewById<LinearLayout>(R.id.ll_help)
        val llLogout = findViewById<LinearLayout>(R.id.ll_logout)

        // --- ASIGNACIÓN DE ACCIONES (LISTENERS) ---

        // Acción para el botón de retroceso en la barra superior
        toolbar.setNavigationOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior.
        }

        // Acción para la opción "Usuari"
        llUser.setOnClickListener {
            val intent = Intent(this, User::class.java)
            startActivity(intent)
        }

        // Acción para la opción "Idioma"
        llLanguage.setOnClickListener {
            val intent = Intent(this, Language::class.java)
            startActivity(intent)
        }

        // Acción para la opción "Notificacions"
        llNotifications.setOnClickListener {
            val intent = Intent(this, Notificacions::class.java)
            startActivity(intent)
        }

        // Acción para la opción "Tancar sesio"
        llLogout.setOnClickListener {
            // Navega a la pantalla de Login y limpia las actividades anteriores.
            val intent = Intent(this, Login::class.java)
            // Estas flags aseguran que el usuario no pueda volver atrás a la app después de cerrar sesión.
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // Acciones para botones que aún no tienen una pantalla (se muestra un mensaje temporal)
        llPremium.setOnClickListener {
            Toast.makeText(this, "Opción Premium próximamente", Toast.LENGTH_SHORT).show()
        }

        llHelp.setOnClickListener {
            Toast.makeText(this, "Sección de Ayuda próximamente", Toast.LENGTH_SHORT).show()
        }
    }
}
