package com.example.fhubo.Films

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.BaseActivity
import com.example.fhubo.City.CityActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Main.MainActivity
import com.example.fhubo.R
import com.example.fhubo.Settings.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class FilmsActivity : BaseActivity() {

    private lateinit var btn_back : ImageView
    private lateinit var bottomMenu : BottomNavigationView
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: FilmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_films)

        btn_back = findViewById(R.id.btn_back_mv)
        btn_back.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.rvFilmLocations)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val films = DataSource.filmLocations
        adapter = FilmsAdapter(
            items = films,
            onItemClick = { item ->
            }
        )
        recyclerView.adapter = adapter

        bottomMenu = findViewById(R.id.bottom_navigation)

        bottomMenu.selectedItemId = R.id.action_film

        bottomMenu.setOnItemSelectedListener { item ->
            val selectedActivity : Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
                R.id.action_profile -> Intent(this, Settings::class.java)
                else -> null
            }

            selectedActivity?.let {
                if (it.component?.className == MainActivity::class.java.name) {
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(it)
            }
            true
        }
    }
}
