package com.example.fhubo.CityLocation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City.CityActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.R
import com.example.fhubo.Settings.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class CityLocationsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityLocationAdapter

    private lateinit var bottomMenu: BottomNavigationView

    private lateinit var btn_back_city : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_locations)

        recyclerView = findViewById(R.id.rvCityLocations)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cities = DataSource.cityLocations
        adapter = CityLocationAdapter(
            items = cities,
            onItemClick = { item ->
                Toast.makeText(
                    this,
                    "Has clicat: ${item.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        recyclerView.adapter = adapter

        btn_back_city = findViewById(R.id.btn_back_city)
        btn_back_city.setOnClickListener {
            finish()
        }

        bottomMenu = findViewById(R.id.bottom_navigation)

        // --- INICI DE LA CORRECCIÓ ---

        // 1. Indiquem quin botó del menú ha d'aparèixer com a seleccionat
        // Com que aquesta pantalla mostra localitzacions de ciutats, deixem marcat l'icona de "Ciutat".
        bottomMenu.selectedItemId = R.id.action_city

        // 2. Configurem el listener per a la navegació
        bottomMenu.setOnItemSelectedListener { item ->
            // A diferencia de les pantalles principals, aquí NO evitem la recàrrega.
            // Si l'usuari està en aquesta pantalla de detall i prem "Ciutat",
            // volem que pugui tornar a la llista principal de ciutats.

            val selectedActivity : Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
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