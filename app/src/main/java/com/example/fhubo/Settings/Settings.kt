package com.example.fhubo.Settings

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fhubo.City.CityActivity
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Login
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class Settings : AppCompatActivity() {

    private lateinit var bottomMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // --- INICIALIZACIÓN DE VISTAS ---
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_language)
        val llUser = findViewById<LinearLayout>(R.id.ll_user)
        val llPremium = findViewById<LinearLayout>(R.id.ll_premium)
        val llLanguage = findViewById<LinearLayout>(R.id.ll_language)
        val llNotifications = findViewById<LinearLayout>(R.id.ll_notifications)
        val llHelp = findViewById<LinearLayout>(R.id.ll_help)
        val llLogout = findViewById<LinearLayout>(R.id.ll_logout)
        bottomMenu = findViewById(R.id.bottom_navigation)

        // --- ASIGNACIÓN DE ACCIONES (LISTENERS) ---

        toolbar.setNavigationOnClickListener {
            finish() // Vuelve a la actividad anterior
        }

        llUser.setOnClickListener {
            startActivity(Intent(this, User::class.java))
        }

        llLanguage.setOnClickListener {
            startActivity(Intent(this, Language::class.java))
        }

        llNotifications.setOnClickListener {
            startActivity(Intent(this, Notificacions::class.java))
        }

        llLogout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        llPremium.setOnClickListener {
            Toast.makeText(this, "Opción Premium próximamente", Toast.LENGTH_SHORT).show()
        }

        llHelp.setOnClickListener {
            Toast.makeText(this, "Sección de Ayuda próximamente", Toast.LENGTH_SHORT).show()
        }

        // --- INICI DE LA CORRECCIÓ ---

        // 1. Indiquem que el botó de "Perfil" ha d'aparèixer com a seleccionat
        bottomMenu.selectedItemId = R.id.action_profile

        // 2. Configurem la navegació del menú inferior
        bottomMenu.setOnItemSelectedListener { item ->
            // Si l'usuari prem el botó on ja es troba, no fem res
            if (item.itemId == R.id.action_profile) {
                return@setOnItemSelectedListener true
            }

            val selectedActivity: Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
                else -> null
            }

            selectedActivity?.let {
                startActivity(it)
            }
            true
        }
        // --- FI DE LA CORRECCIÓ ---
    }
}