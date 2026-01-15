package com.example.fhubo.Main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City.CityActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Popup2
import com.example.fhubo.R
import com.example.fhubo.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var helpButton: ImageView

    private lateinit var recyclerView : RecyclerView

    private lateinit var adapter: MainAdapter

    private lateinit var bottomMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helpButton = findViewById(R.id.ivHelp)
        bottomMenu = findViewById(R.id.bottom_navigation)
        recyclerView = findViewById(R.id.rvFilms)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val films = DataSource.films
        adapter = MainAdapter(
            items = films,
            onItemClick = { item ->
                Toast.makeText(
                    this,
                    "Has clicat: ${item.imageResource1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        recyclerView.adapter = adapter



        helpButton.setOnClickListener {
            val intent = Intent(this, Popup2::class.java)
            startActivity(intent)
        }

        bottomMenu.setOnItemSelectedListener { item ->
            val selectedActivity : Intent? = when (item.itemId) {
                R.id.action_film -> Intent(this, MainActivity::class.java)
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
                R.id.action_profile -> Intent(this, Settings::class.java)
                else -> Intent(this, CityActivity::class.java)
            }
            startActivity(selectedActivity)
            true
        }
    }


}
