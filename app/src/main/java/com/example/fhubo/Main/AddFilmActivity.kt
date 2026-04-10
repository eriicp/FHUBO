package com.example.fhubo.Main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import com.example.fhubo.Stats.FhuboStatsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFilmActivity : BaseActivity() {

    private lateinit var etName: EditText
    private lateinit var etCategory: EditText
    private lateinit var etYear: EditText
    private lateinit var etImagePath: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        etName = findViewById(R.id.etName)
        etCategory = findViewById(R.id.etCategory)
        etYear = findViewById(R.id.etYear)
        etImagePath = findViewById(R.id.etImagePath)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            saveFilm()
        }
    }

    private fun saveFilm() {
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
                    ItemAPI.API().insertFilm(filmRequest)
                }
                if (response.isSuccessful) {
                    val message = response.body()?.string() ?: "Guardat"
                    Toast.makeText(this@AddFilmActivity, message, Toast.LENGTH_SHORT).show()
                    
                    // Registrar l'estadística de la pel·lícula afegida per categoria
                    FhuboStatsProvider.afegirPelicula(category)

                    finish() 
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Error ${response.code()}"
                    Log.e("API_ERROR", errorMsg)
                    Toast.makeText(this@AddFilmActivity, "Error al guardar: $errorMsg", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Error: ${e.message}")
                Toast.makeText(this@AddFilmActivity, "Error de connexió: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
