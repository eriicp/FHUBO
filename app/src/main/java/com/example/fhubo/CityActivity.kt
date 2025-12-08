package com.example.fhubo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City
import com.example.fhubo.CityAdapter
import com.example.fhubo.R
    
class CityActivity : AppCompatActivity() {

    private lateinit var rvCities: RecyclerView
    private lateinit var svCity: SearchView
    private lateinit var adapter: CityAdapter
    private lateinit var toolbar: Toolbar // 1. Declara la Toolbar

    private val allCities = listOf(
        City("BARCELONA", R.drawable.city_barcelona),
        City("PARIS", R.drawable.city_paris),
        City("ROMA", R.drawable.city_roma),
        City("BERLIN", R.drawable.city_berlin)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        // --- 2. INICIALIZA Y CONFIGURA LA TOOLBAR ---
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // ¡Línea clave! Establece esta Toolbar como la barra de acción principal de la actividad.

        // Quita el título por defecto de la Toolbar si no lo quieres
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Inicializar otras vistas
        rvCities = findViewById(R.id.rvCities)
        svCity = findViewById(R.id.svCity)

        // Configurar RecyclerView
        adapter = CityAdapter(allCities)
        rvCities.layoutManager = LinearLayoutManager(this)
        rvCities.adapter = adapter

        // Configurar SearchView
        initSearchView()
    }

    // --- 3. INFLA EL MENÚ EN LA TOOLBAR ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Infla el menú que creaste; esto añade los ítems a la barra de acción.
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    // --- 4. MANEJA LOS CLICS EN LOS ÍTEMS DEL MENÚ ---
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Se llama cada vez que se pulsa un ítem del menú.
        // El 'when' comprueba el ID del ítem pulsado.
        return when (item.itemId) {
            R.id.action_film -> {
                Toast.makeText(this, "Has pulsado Home", Toast.LENGTH_SHORT).show()
                true // Indica que hemos manejado el clic
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
            else -> super.onOptionsItemSelected(item) // Si no reconocemos el ítem, dejamos que el sistema lo maneje.
        }
    }

    // Tu función initSearchView() no cambia
    private fun initSearchView() {
        // ...
    }
}
