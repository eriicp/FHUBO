package com.example.fhubo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Inicialización de vistas ---


        // Vistas para hacer clicables
        val ivFilmName = findViewById<ImageView>(R.id.ivFilmName)
        val recycler3puntos = findViewById<ImageView>(R.id.tresPuntos)
        val helpButton = findViewById<ImageView>(R.id.ivHelp)


        // --- Asignación de acciones (Listeners) ---

        // Al pulsar en la imagen de la película, se abre FilmActivity
        ivFilmName.setOnClickListener {
            val intent = Intent(this, FilmsActivity::class.java)
            startActivity(intent)
        }

        // Al pulsar en los tres puntos, se muestra un mensaje (puedes cambiar esta acción)
        recycler3puntos.setOnClickListener {
            val intent = Intent(this, PopUpHelp1::class.java)
            startActivity(intent)
        }

        // --- Acciones de la barra de menú inferior ---


        helpButton.setOnClickListener {
            val intent = Intent(this, Popup2::class.java)
            startActivity(intent)
        }
    }
}
