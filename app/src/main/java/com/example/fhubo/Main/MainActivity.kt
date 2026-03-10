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

    private var currentCategory: String = "Totes"
    private var currentSortOrder: String = SORT_NONE

    private lateinit var helpButton: ImageView
    private lateinit var btnFilter: ImageButton
    private lateinit var svMain: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var bottomMenu: BottomNavigationView

    companion object {
        const val SORT_NONE = "none"
        const val SORT_YEAR_DESC = "year_desc"
        const val SORT_YEAR_ASC = "year_asc"
    }

    suspend fun fetchFilms(): List<Main> {
        val response = ItemAPI.API().llistaFilms()
        return response.body() ?: emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSearchView()
        setupRecyclerView()
        setupListeners()
        setupBottomNavigation()
        performSearch(null)
    }

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
            items = emptyList(),
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        performSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        performSearch(newText)
        return true
    }

    private fun showCategoryPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.popup_categories, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cat_totes -> currentCategory = "Totes"
                R.id.cat_peliculas -> currentCategory = "Pel·lícules"
                R.id.cat_llibres -> currentCategory = "Llibres"
                R.id.cat_musica -> currentCategory = "Música"
                R.id.sort_year_desc -> currentSortOrder = SORT_YEAR_DESC
                R.id.sort_year_asc -> currentSortOrder = SORT_YEAR_ASC
            }
            performSearch(svMain.query.toString())
            true
        }
        popup.show()
    }

    private fun performSearch(query: String?) {
        val allItems = DataSource.films

        val categorizedList = if (currentCategory == "Totes") {
            allItems
        } else {
            allItems.filter { it.category.equals(currentCategory, ignoreCase = true) }
        }

        val filteredList = if (query.isNullOrBlank()) {
            categorizedList
        } else {
            categorizedList.filter { it.name.contains(query, ignoreCase = true) }
        }

        val sortedList = when (currentSortOrder) {
            SORT_YEAR_DESC -> filteredList.sortedByDescending { it.year }
            SORT_YEAR_ASC -> filteredList.sortedBy { it.year }
            else -> filteredList
        }

        adapter.updateList(sortedList)
    }
}
