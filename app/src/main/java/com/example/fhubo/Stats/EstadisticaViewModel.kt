package com.example.fhubo.Stats

import androidx.lifecycle.*
import com.example.fhubo.Stats.FhuboEstadistica
import com.example.fhubo.Stats.FhuboStatsProvider
import kotlinx.coroutines.launch

class EstadistiquesViewModel : ViewModel() {
    private val _estadistiques = MutableLiveData<FhuboEstadistica>()
    val estadistiques: LiveData<FhuboEstadistica> get() = _estadistiques

    fun carregarDades(idUsuari: String) {
        viewModelScope.launch {
            val resultat = FhuboStatsProvider.carregarEstadistica(idUsuari)
            if (resultat.isSuccess) {
                _estadistiques.value = resultat.getOrNull()
            } else {
                _estadistiques.value = FhuboStatsProvider.dataEstadistica // Dades per defecte
            }
        }
    }

    fun guardarDades(idUsuari: String) {
        viewModelScope.launch {
            FhuboStatsProvider.guardarEstadistica(idUsuari)
        }
    }
}