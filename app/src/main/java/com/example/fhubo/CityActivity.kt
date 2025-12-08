package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City
import com.example.fhubo.CityAdapter
import com.example.fhubo.R
import java.util.Locale

class CityActivity : AppCompatActivity() {

    /*
    private lateinit var rvCities: RecyclerView
    private lateinit var svCity: SearchView
    private lateinit var adapter: CityAdapter
    // private lateinit var toolbar: Toolbar // Comentado para uso futuro

    private val allCities = listOf(
        City("BARCELONA", R.drawable.city_barcelona),
        City("PARIS", R.drawable.city_paris),
        City("ROMA", R.drawable.city_roma),
        City("BERLIN", R.drawable.city_berlin)
    )
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        /*
        // --- INICIALIZACIÓN Y CONFIGURACIÓN DE LA TOOLBAR (Comentado) ---
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        */

        /*
        // Inicializar otras vistas
        rvCities = findViewById(R.id.rvCities)
        svCity = findViewById(R.id.svCity)

        // Configurar RecyclerView
        adapter = CityAdapter(allCities)
        rvCities.layoutManager = LinearLayoutManager(this)
        rvCities.adapter = adapter

        // Configurar SearchView
        initSearchView()
        */
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

    /*
    // --- LÓGICA DEL MENÚ DE LA TOOLBAR (Comentado para uso futuro) ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_film -> {
                Toast.makeText(this, "Has pulsado Home", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_favorite -> {
                Toast.makeText(this, "Has pulsado Favoritos", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_city -> {
                Toast.makeText(this, "Has pulsado Mapa", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_profile -> {
                Toast.makeText(this, "Has pulsado Perfil", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    */

    /*
    private fun initSearchView() {
        svCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.lowercase(Locale.getDefault()) ?: ""
                val filteredCities = allCities.filter { city ->
                    city.name.lowercase(Locale.getDefault()).contains(query)
                }
                adapter.updateList(filteredCities)
                return true
            }
        })
    }
    */
}
