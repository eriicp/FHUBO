package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fhubo.Main.EditFilmActivity
import com.example.fhubo.Main.ItemAPI
import com.example.fhubo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopUpHelp1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_help1)

        val filmId = intent.getLongExtra("FILM_ID", -1L)
        val name = intent.getStringExtra("FILM_NAME")
        val category = intent.getStringExtra("FILM_CATEGORY")
        val year = intent.getIntExtra("FILM_YEAR", 0)
        val imagePath = intent.getStringExtra("FILM_IMAGE_PATH")

        findViewById<TextView>(R.id.tv_edit).setOnClickListener {
            if (filmId != -1L) {
                val intent = Intent(this, EditFilmActivity::class.java)
                intent.putExtra("FILM_ID", filmId)
                intent.putExtra("FILM_NAME", name)
                intent.putExtra("FILM_CATEGORY", category)
                intent.putExtra("FILM_YEAR", year)
                intent.putExtra("FILM_IMAGE_PATH", imagePath)
                startActivity(intent)
                finish()
            }
        }

        findViewById<TextView>(R.id.tv_delete).setOnClickListener {
            if (filmId != -1L) {
                deleteFilm(filmId)
            } else {
                finish()
            }
        }

        findViewById<TextView>(R.id.tv_add_favorite).setOnClickListener {
            Toast.makeText(this, "Afegit a favorits", Toast.LENGTH_SHORT).show()
            finish()
        }


    }

    private fun deleteFilm(id: Long) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ItemAPI.API().deleteFilm(id)
                }
                if (response.isSuccessful) {
                    Toast.makeText(this@PopUpHelp1, "Eliminat correctament", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PopUpHelp1, "Error de connexió", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
