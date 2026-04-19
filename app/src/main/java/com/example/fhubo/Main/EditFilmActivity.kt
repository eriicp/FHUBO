package com.example.fhubo.Main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditFilmActivity : BaseActivity() {

    private lateinit var etName: EditText
    private lateinit var etCategory: EditText
    private lateinit var etYear: EditText
    private lateinit var etImagePath: EditText
    private lateinit var btnSave: Button
    private lateinit var tvTitle: TextView
    private var filmId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        etName = findViewById(R.id.etName)
        etCategory = findViewById(R.id.etCategory)
        etYear = findViewById(R.id.etYear)
        etImagePath = findViewById(R.id.etImagePath)
        btnSave = findViewById(R.id.btnSave)
        tvTitle = findViewById(R.id.tvAddTitle)

        tvTitle.text = "Editar Pel·lícula"
        
        filmId = intent.getLongExtra("FILM_ID", -1L)
        val name = intent.getStringExtra("FILM_NAME")
        val category = intent.getStringExtra("FILM_CATEGORY")
        val year = intent.getIntExtra("FILM_YEAR", 0)
        val imagePath = intent.getStringExtra("FILM_IMAGE_PATH")

        etName.setText(name)
        etCategory.setText(category)
        etYear.setText(year.toString())
        etImagePath.setText(imagePath)

        btnSave.setOnClickListener {
            updateFilm()
        }
    }

    private fun updateFilm() {
        val name = etName.text.toString()
        val category = etCategory.text.toString()
        val yearString = etYear.text.toString()
        val imagePath = etImagePath.text.toString()

        if (name.isBlank() || category.isBlank() || yearString.isBlank() || imagePath.isBlank()) {
            Toast.makeText(this, "Omple tots els camps", Toast.LENGTH_SHORT).show()
            return
        }

        val year = yearString.toIntOrNull() ?: 0
        val filmRequest = FilmRequest(name = name, category = category, year = year, imagePath = imagePath)

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ItemAPI.API().updateFilm(filmId, filmRequest)
                }
                if (response.isSuccessful) {
                    val message = response.body()?.string() ?: "Actualitzat"
                    Toast.makeText(this@EditFilmActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Error ${response.code()}"
                    Toast.makeText(this@EditFilmActivity, "Error: $errorMsg", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@EditFilmActivity, "Error de connexió", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
