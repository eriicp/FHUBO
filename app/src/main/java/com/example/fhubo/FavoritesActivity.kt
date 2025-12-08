package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnFilms = findViewById<ImageView>(R.id.btnFilms)
        val btnLocations = findViewById<ImageView>(R.id.btnLocations)
        val btnFavorites = findViewById<ImageView>(R.id.btnFavorites)
        val btnProfile = findViewById<ImageView>(R.id.btnProfile)

        btnFilms.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir a la lista de ciudades
        btnLocations.setOnClickListener {
            val intent = Intent(this, CityActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir a la pantalla de favoritos
        btnFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir a la pantalla de configuración/perfil
        btnProfile.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }
}