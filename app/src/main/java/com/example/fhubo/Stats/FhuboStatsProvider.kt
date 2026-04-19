package com.example.fhubo.Stats

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object FhuboStatsProvider {
    val db: FirebaseFirestore by lazy { Firebase.firestore }
    var dataEstadistica = FhuboEstadistica()
    var dadesCarregades = false 

    suspend fun carregarEstadistica(idUsuari: String): Result<FhuboEstadistica> {
        return try {
            val doc = db.collection("FhuboStats").document(idUsuari).get().await()
            val valor = doc.toObject(FhuboEstadistica::class.java)
            
            if (valor != null) {
                // Si ja teníem dades d'aquesta sessió (ex: visita a MainActivity), les sumem a les de Firebase
                if (!dadesCarregades) {
                    valor.minutsUsTotal += dataEstadistica.minutsUsTotal
                    valor.co2Total += dataEstadistica.co2Total
                    valor.peliculesAfegides += dataEstadistica.peliculesAfegides
                    
                    dataEstadistica.vistesPerActivity.forEach { (k, v) ->
                        valor.vistesPerActivity[k] = (valor.vistesPerActivity[k] ?: 0) + v
                    }
                    dataEstadistica.tempsPerPestanya.forEach { (k, v) ->
                        valor.tempsPerPestanya[k] = (valor.tempsPerPestanya[k] ?: 0f) + v
                    }
                }
                dataEstadistica = valor
                dadesCarregades = true
                Result.success(valor)
            } else {
                dadesCarregades = true
                Result.success(dataEstadistica)
            }
        } catch (e: Exception) {
            Result.failure<FhuboEstadistica>(e)
        }
    }

    suspend fun guardarEstadistica(idUsuari: String): Result<Unit> {
        // Eliminada la restricción estricta. Si no se cargó, se intenta guardar lo actual.
        // Pero idealmente deberíamos asegurar la carga previa en la App.
        return try {
            db.collection("FhuboStats").document(idUsuari).set(dataEstadistica).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure<Unit>(e)
        }
    }

    fun registrarVisita(nomActivity: String) {
        val visitesActuals = dataEstadistica.vistesPerActivity[nomActivity] ?: 0
        dataEstadistica.vistesPerActivity[nomActivity] = visitesActuals + 1
    }

    fun sumarMinutsUs(minuts: Float) {
        dataEstadistica.minutsUsTotal += minuts
        // Factor de conversión orientativo: min -> h * kWh/h * gCO2/kWh
        val gCo2Afegit = (minuts / 60.0f) * 0.002f * 233.0f
        dataEstadistica.co2Total += gCo2Afegit
    }

    fun registrarTempsPestanya(nomPestanya: String, minuts: Float) {
        val tempsActual = dataEstadistica.tempsPerPestanya[nomPestanya] ?: 0f
        dataEstadistica.tempsPerPestanya[nomPestanya] = tempsActual + minuts
    }

    fun afegirPelicula(categoria: String) {
        dataEstadistica.peliculesAfegides++
        val count = dataEstadistica.peliculesPerCategoria[categoria] ?: 0
        dataEstadistica.peliculesPerCategoria[categoria] = count + 1
    }

    fun borrarDades() {
        dataEstadistica = FhuboEstadistica()
        dadesCarregades = true
    }
}
