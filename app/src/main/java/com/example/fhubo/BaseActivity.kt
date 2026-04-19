package com.example.fhubo

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fhubo.Stats.FhuboStatsProvider
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {
    protected var tempsInici: Long = 0

    override fun onResume() {
        super.onResume()
        // Guarda quina activity s'està veient
        val activityName = this.javaClass.simpleName
        FhuboStatsProvider.registrarVisita(activityName)
        // Marca el temps d'inici
        tempsInici = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        // Calcula els minuts que ha passat a l'activitat i suma'ls
        val tempsFinal = System.currentTimeMillis()
        val milisegons = (tempsFinal - tempsInici)
        val minutsFloat = milisegons.toFloat() / 60000f

        FhuboStatsProvider.sumarMinutsUs(minutsFloat)
        
        // Registrar temps per la secció específica (nom de l'activitat)
        val activityName = this.javaClass.simpleName
        FhuboStatsProvider.registrarTempsPestanya(activityName, minutsFloat)

        // Desa a Firebase en sortir
        lifecycleScope.launch {
            FhuboStatsProvider.guardarEstadistica("usuariTest")
        }
    }
}