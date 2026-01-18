package com.example.fhubo.Main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.City.CityActivity
import com.example.fhubo.CityLocation.CityLocationsActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.Films.FilmsActivity
import com.example.fhubo.R
import com.example.fhubo.Settings.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    // --- Variables de estado para el filtro ---
    private var currentCategory: String = "Totes"
    private var currentSortOrder: String = SORT_NONE // Nuevo: para el orden

    // --- Vistas ---
    private lateinit var helpButton: ImageView
    private lateinit var btnFilter: ImageButton
    private lateinit var svMain: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var bottomMenu: BottomNavigationView

    companion object {
        // Constantes para la ordenación
        const val SORT_NONE = "none"
        const val SORT_YEAR_DESC = "year_desc"
        const val SORT_YEAR_ASC = "year_asc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inicializar las vistas
        initViews()

        // 2. Corregir el color del buscador
        setupSearchView()

        // 3. Configurar RecyclerView
        setupRecyclerView()

        // 4. Configurar Listeners
        setupListeners()

        // 5. Configurar el menú de navegación inferior
        setupBottomNavigation()
        
        // Carga inicial
        performSearch(null)
    }

    // --- Métodos de configuración ---

    private fun initViews() {
        helpButton = findViewById(R.id.ivHelp)
        btnFilter = findViewById(R.id.btnFilter)
        svMain = findViewById(R.id.svMain)
        bottomMenu = findViewById(R.id.bottom_navigation)
        recyclerView = findViewById(R.id.rvFilms)
    }

    private fun setupSearchView() {
        val acompleteTextView = svMain.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        acompleteTextView.setTextColor(Color.WHITE)
        acompleteTextView.setHintTextColor(Color.LTGRAY)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(
            items = emptyList(), // Empezamos con una lista vacía
            onItemClick = { film ->
                val intent = when (film.name) {
                    "Star Wars" -> Intent(this, FilmsActivity::class.java)
                    "Hunger Games" -> Intent(this, CityLocationsActivity::class.java)
                    "El Código Da Vinci" -> Intent(this, CityLocationsActivity::class.java)
                    "Harry Potter" -> Intent(this, CityLocationsActivity::class.java)
                    else -> null
                }
                intent?.let { startActivity(it) }
            },
            onThreeDotsClick = { film ->
                FilmOptionsDialogFragment().show(supportFragmentManager, "FilmOptionsDialog")
            }
        )
        recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        btnFilter.setOnClickListener { showCategoryPopupMenu(it) }
        svMain.setOnQueryTextListener(this)
    }

    private fun setupBottomNavigation() {
        bottomMenu.selectedItemId = R.id.action_film
        bottomMenu.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.action_film) return@setOnItemSelectedListener true
            val intent = when (item.itemId) {
                R.id.action_city -> Intent(this, CityActivity::class.java)
                R.id.action_favorite -> Intent(this, FavoritesActivity::class.java)
                R.id.action_profile -> Intent(this, Settings::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
            true
        }
    }

    // --- Implementación de la búsqueda (SearchView) ---
    override fun onQueryTextSubmit(query: String?): Boolean {
        performSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        performSearch(newText)
        return true
    }

    // --- Lógica de filtrado y búsqueda ---
    private fun showCategoryPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.popup_categories, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                // Opciones de Categoría
                R.id.cat_totes -> currentCategory = "Totes"
                R.id.cat_peliculas -> currentCategory = "Pel·lícules"
                R.id.cat_llibres -> currentCategory = "Llibres"
                R.id.cat_musica -> currentCategory = "Música"
                // Opciones de Ordenación
                R.id.sort_year_desc -> currentSortOrder = SORT_YEAR_DESC
                R.id.sort_year_asc -> currentSortOrder = SORT_YEAR_ASC
            }
            // Reaplicamos el filtro y la búsqueda con los nuevos criterios
            performSearch(svMain.query.toString())
            true
        }
        popup.show()
    }

    private fun performSearch(query: String?) {
        val allItems = DataSource.films

        // Criteri 1: Filtrar por Categoría
        val categorizedList = if (currentCategory == "Totes") {
            allItems
        } else {
            allItems.filter { it.category.equals(currentCategory, ignoreCase = true) }
        }

        // Criteri 2: Filtrar por Texto de búsqueda
        val filteredList = if (query.isNullOrBlank()) {
            categorizedList
        } else {
            categorizedList.filter { it.name.contains(query, ignoreCase = true) }
        }

        // Criteri 3: Ordenar por Año
        val sortedList = when (currentSortOrder) {
            SORT_YEAR_DESC -> filteredList.sortedByDescending { it.year }
            SORT_YEAR_ASC -> filteredList.sortedBy { it.year }
            else -> filteredList // Sin orden específico
        }

        adapter.updateList(sortedList)
    }
}