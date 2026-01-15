package com.example.fhubo.City

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.CityLocation.CityLocationsActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.R
import com.example.fhubo.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class CityActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    private lateinit var bottomMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        recyclerView = findViewById(R.id.rvCities)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cities = DataSource.cities
        adapter = CityAdapter(
            items = cities,
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
        bottomMenu.setOnItemSelectedListener { item ->
            val selectedActivity : Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
                R.id.action_profile -> Intent(this, Settings::class.java)
                else -> Intent(this,CityActivity::class.java)
            }
            startActivity(selectedActivity)
            true
        }
    }
}
