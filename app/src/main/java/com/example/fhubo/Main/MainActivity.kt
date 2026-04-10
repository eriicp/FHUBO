package com.example.fhubo.Main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.BaseActivity
import com.example.fhubo.City.CityActivity
import com.example.fhubo.DataSource
import com.example.fhubo.Favorites.FavoritesActivity
import com.example.fhubo.PopUpHelp1
import com.example.fhubo.R
import com.example.fhubo.Settings.Settings
import com.example.fhubo.Stats.FhuboStatsProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private var currentCategory: String = "Totes"
    private var currentSortOrder: String = SORT_NONE
    private var allFilms: List<Main> = DataSource.films

    private lateinit var btnFilter: ImageButton
    private lateinit var btnAdd: ImageButton
    private lateinit var svMain: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonVoice: ImageButton
    private lateinit var adapter: MainAdapter
    private lateinit var bottomMenu: BottomNavigationView
    private lateinit var recognizer: SpeechRecognizer

    private val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
    }

    companion object {
        const val SORT_NONE = "none"
        const val SORT_YEAR_DESC = "year_desc"
        const val SORT_YEAR_ASC = "year_asc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // CARREGAR ESTADÍSTIQUES DE FIREBASE AL PRINCIPI DE L'APP
        // Així evitem sobrescriure amb dades buides i mantenim l'històric
        lifecycleScope.launch {
            FhuboStatsProvider.carregarEstadistica("usuariTest")
        }

        initViews()
        
        recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        
        setupSearchView()
        setupRecyclerView()
        setupListeners()
        setupBottomNavigation()

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val spokenText = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.get(0)
                    ?.lowercase()

                handleVoiceCommand(spokenText)
            }

            override fun onError(error: Int) {}
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        loadFilms()
    }

    private fun initViews() {
        btnFilter = findViewById(R.id.btnFilter)
        btnAdd = findViewById(R.id.btnAdd)
        svMain = findViewById(R.id.svMain)
        bottomMenu = findViewById(R.id.bottom_navigation)
        recyclerView = findViewById(R.id.rvFilms)
        buttonVoice = findViewById(R.id.buttonVoice)
    }

    private fun setupSearchView() {
        val acompleteTextView = svMain.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        acompleteTextView.setTextColor(Color.WHITE)
        acompleteTextView.setHintTextColor(Color.LTGRAY)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(
            items = allFilms,
            onItemClick = { film ->
                Toast.makeText(this, "Clicked: ${film.name}", Toast.LENGTH_SHORT).show()
            },
            onThreeDotsClick = { film ->
                val intent = Intent(this, PopUpHelp1::class.java)
                intent.putExtra("FILM_ID", film.id)
                intent.putExtra("FILM_NAME", film.name)
                intent.putExtra("FILM_CATEGORY", film.category)
                intent.putExtra("FILM_YEAR", film.year)
                intent.putExtra("FILM_IMAGE_PATH", film.imagePath)
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        btnFilter.setOnClickListener { showCategoryPopupMenu(it) }
        buttonVoice.setOnClickListener {
            recognizer.startListening(recognizerIntent)
        }
        svMain.setOnQueryTextListener(this)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddFilmActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupBottomNavigation() {
        bottomMenu.selectedItemId = R.id.action_film
        bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_film -> true
                R.id.action_city -> {
                    startActivity(Intent(this, CityActivity::class.java))
                    true
                }
                R.id.action_favorite -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    true
                }
                R.id.action_profile -> {
                    startActivity(Intent(this, Settings::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFilms() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ItemAPI.API().llistaFilms()
                }
                if (response.isSuccessful) {
                    val apiFilms = response.body()
                    if (!apiFilms.isNullOrEmpty()) {
                        allFilms = apiFilms
                        performSearch(svMain.query.toString())
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
            }
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
    private fun handleVoiceCommand(command: String?) {
        when {
            command?.contains("apagar") == true -> {
            finish()
            }
            command?.contains(" enrere") == true -> {
                onBackPressedDispatcher.onBackPressed()
            }
            command?.contains("acceptar") == true -> {
                // Guardar dades, fer submit, etc.
            }
        }
    }
    private fun showCategoryPopupMenu(view: View) {
        val popup = PopupMenu(this, view)

        // 1. Obtenim totes les categories úniques que hi ha actualment a la BBDD (allFilms)
        val uniqueCategories = allFilms.map { it.category }.distinct().sorted()

        // 2. Creem dinàmicament el menú
        val menu = popup.menu

        // Afegim l'opció per defecte "Totes"
        val subMenuCategories = menu.addSubMenu("Categories")
        subMenuCategories.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Totes")

        // Afegim les categories que hem trobat a la BBDD
        uniqueCategories.forEachIndexed { index, category ->
            subMenuCategories.add(Menu.NONE, Menu.FIRST + index + 1, Menu.NONE, category)
        }

        // Afegim les opcions d'ordenació
        val subMenuSort = menu.addSubMenu("Ordenar per")
        subMenuSort.add(Menu.NONE, 100, Menu.NONE, "Any (nous primer)")
        subMenuSort.add(Menu.NONE, 101, Menu.NONE, "Any (antics primer)")

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                100 -> currentSortOrder = SORT_YEAR_DESC
                101 -> currentSortOrder = SORT_YEAR_ASC
                Menu.FIRST -> currentCategory = "Totes"
                else -> {
                    // Si l'ID està entre FIRST+1 i el final de les categories, és una categoria dinàmica
                    if (menuItem.itemId > Menu.FIRST && menuItem.itemId <= Menu.FIRST + uniqueCategories.size) {
                        currentCategory = uniqueCategories[menuItem.itemId - Menu.FIRST - 1]
                    }
                }
            }
            performSearch(svMain.query.toString())
            true
        }
        popup.show()
    }



    private fun performSearch(query: String?) {
        val categorizedList = if (currentCategory == "Totes") {
            allFilms
        } else {
            allFilms.filter { it.category.equals(currentCategory, ignoreCase = true) }
        }

        val filteredList = if (query.isNullOrBlank()) {
            categorizedList
        } else {
            categorizedList.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.year.toString().contains(query) ||
                it.category.contains(query, ignoreCase = true)
            }
        }

        val sortedList = when (currentSortOrder) {
            SORT_YEAR_DESC -> filteredList.sortedByDescending { it.year }
            SORT_YEAR_ASC -> filteredList.sortedBy { it.year }
            else -> filteredList
        }

        adapter.updateList(sortedList)
    }

    fun deleteFilm(id: Long) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ItemAPI.API().deleteFilm(id)
                }
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Eliminada correctament", Toast.LENGTH_SHORT).show()
                    loadFilms()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error de connexió", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
