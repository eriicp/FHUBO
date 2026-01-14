package com.example.fhubo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CityLocationsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    private lateinit var btn_back_city : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_locations)

        recyclerView = findViewById(R.id.rvCityLocations)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cities = DataSource.cities
        adapter = CityAdapter(
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

        btn_back_city = findViewById<ImageButton>(R.id.btn_back_city)
        btn_back_city.setOnClickListener {
            finish()
        }
    }
}