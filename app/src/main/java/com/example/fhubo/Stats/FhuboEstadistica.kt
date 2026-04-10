package com.example.fhubo.Stats

data class FhuboEstadistica(
    var minutsUsTotal: Float = 0f,
    var co2Total: Float = 0f, // Nou camp per al CO2 en grams
    var vistesPerActivity: HashMap<String, Int> = hashMapOf(), // Activity més vista
    var tempsPerPestanya: HashMap<String, Float> = hashMapOf(), // Temps d'ús per pestanya en minuts
    var peliculesAfegides: Int = 0, // Total de pel·lícules
    var peliculesPerCategoria: HashMap<String, Int> = hashMapOf() // Pel·lícules per categoria
)