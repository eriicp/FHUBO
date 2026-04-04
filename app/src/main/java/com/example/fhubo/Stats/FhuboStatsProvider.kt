package com.example.fhubo.Stats

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import kotlinx.coroutines.tasks.await

object FhuboStatsProvider {
    val db: FirebaseFirestore by lazy { Firebase.firestore }
    var dataEstadistica = FhuboEstadistica()

    suspend fun carregarEstadistica(idUsuari: String): Result<FhuboEstadistica> {
        return try {
            val doc = db.collection("FhuboStats").document(idUsuari).get().await()
            val valor = doc.toObject(FhuboEstadistica::class.java)
            if (valor != null) {
                dataEstadistica = valor
                Result.success(valor)
            } else {
                Result.failure<FhuboEstadistica>(Exception("Estadística no trobada"))
            }
        } catch (e: Exception) {
            Result.failure<FhuboEstadistica>(e)
        }
    }

    suspend fun guardarEstadistica(idUsuari: String): Result<Unit> {
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
}