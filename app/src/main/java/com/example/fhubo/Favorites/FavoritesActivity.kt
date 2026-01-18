package com.example.fhubo.Favorites

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City.CityActivity
import com.example.fhubo.City.CityAdapter
import com.example.fhubo.CityLocation.CityLocationsActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.R
import com.example.fhubo.Settings.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: FavoritesAdapter

    private lateinit var bottomMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)

        recyclerView = findViewById(R.id.rvfavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val favorites = DataSource.favorites
        adapter = FavoritesAdapter(
            items = favorites,
            onItemClick = { item ->
                when (item.name){
                    "Barcelona" -> intent = Intent(this, Settings::class.java)
                    "Paris" -> intent = Intent(this, CityLocationsActivity::class.java)
                    "Roma" -> intent = Intent(this, CityLocationsActivity::class.java)
                    "Berlin" -> intent = Intent(this, CityLocationsActivity::class.java)
                }
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter

        bottomMenu = findViewById(R.id.bottom_navigation)

        // --- INICI DE LA CORRECCIÓ ---

        // 1. Indiquem quin botó del menú ha d'aparèixer com a seleccionat
        bottomMenu.selectedItemId = R.id.action_favorite

        // 2. Configurem el listener per a la navegació
        bottomMenu.setOnItemSelectedListener { item ->
            // Si l'usuari prem el botó de la pantalla on ja es troba, no fem res
            if (item.itemId == R.id.action_favorite) {
                return@setOnItemSelectedListener true
            }

            val selectedActivity : Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_profile -> Intent(this, Settings::class.java)
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