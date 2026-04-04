package com.example.fhubo.Stats

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class EstadistiquesActivity : BaseActivity() {

    private val vmodel: EstadistiquesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadistiques)

        val idUsuari = "usuariTest"
        vmodel.carregarDades(idUsuari)

        vmodel.estadistiques.observe(this) { estadistiques ->
            mostrarDadesEnergia(estadistiques.minutsUsTotal)
            dibuixarGraficActivities(estadistiques.vistesPerActivity)
            dibuixarGraficSeccions(estadistiques.tempsPerPestanya)
            dibuixarGraficPelicules(estadistiques.peliculesPerCategoria)
        }

        // Botó per borrar dades (Dalt a la dreta)
        findViewById<ImageButton>(R.id.btn_borrar_dades).setOnClickListener {
            borrarDades(idUsuari)
        }

        // Botó per pujar a Firebase (Abaix)
        findViewById<MaterialButton>(R.id.btn_pujar_firebase).setOnClickListener {
            pujarAFirebase(idUsuari)
        }
    }

    private fun borrarDades(idUsuari: String) {
        FhuboStatsProvider.dataEstadistica = FhuboEstadistica()
        // Opcional: També podem forçar el guardat a Firebase després de borrar
        lifecycleScope.launch {
            val result = FhuboStatsProvider.guardarEstadistica(idUsuari)
            if (result.isSuccess) {
                Toast.makeText(this@EstadistiquesActivity, "Dades esborrades", Toast.LENGTH_SHORT).show()
                vmodel.carregarDades(idUsuari) // Recarregar UI
            }
        }
    }

    private fun pujarAFirebase(idUsuari: String) {
        lifecycleScope.launch {
            val result = FhuboStatsProvider.guardarEstadistica(idUsuari)
            if (result.isSuccess) {
                Toast.makeText(this@EstadistiquesActivity, "Dades pujades correctament", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@EstadistiquesActivity, "Error al pujar dades", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarDadesEnergia(minuts: Float) {
        val hores = minuts / 60.0
        val energiaKWh = hores * 0.002
        val gCo2 = energiaKWh * 233.0

        val textCo2 = findViewById<TextView>(R.id.txtEnergiaCo2)
        textCo2.text = "Temps d'ús: ${hores.format(2)} hores\n" +
                "CO2 generat: ${gCo2.format(2)} g"
    }

    private fun dibuixarGraficActivities(vistes: HashMap<String, Int>) {
        val barChart = findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.barChartActivities)
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        var index = 0f
        for ((activityName, count) in vistes) {
            entries.add(BarEntry(index, count.toFloat()))
            labels.add(activityName)
            index++
        }

        val dataSet = BarDataSet(entries, "Visites")
        dataSet.colors = listOf(Color.rgb(135, 206, 250), Color.rgb(255, 182, 193))

        val barData = BarData(dataSet)
        barChart.data = barData

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setGranularity(1f)
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.invalidate()
    }

    private fun dibuixarGraficPelicules(peliculesPerCategoria: HashMap<String, Int>) {
        val pieChart = findViewById<com.github.mikephil.charting.charts.PieChart>(R.id.pieChartCustom)
        val entries = ArrayList<PieEntry>()

        for ((categoria, count) in peliculesPerCategoria) {
            if (count > 0) {
                entries.add(PieEntry(count.toFloat(), categoria))
            }
        }

        if (entries.isEmpty()) {
            entries.add(PieEntry(1f, "Cap pel·lícula"))
        }

        val dataSet = PieDataSet(entries, "Pel·lícules per Categoria")
        dataSet.colors = listOf(
            Color.rgb(255, 102, 102),
            Color.rgb(102, 255, 102),
            Color.rgb(102, 102, 255),
            Color.rgb(255, 255, 102),
            Color.rgb(255, 102, 255)
        )
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.description.isEnabled = false
        pieChart.centerText = "Categoris"
        pieChart.animateY(1000)
        pieChart.invalidate()
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun dibuixarGraficSeccions(tempsPestanya: HashMap<String, Float>) {
        val pieChart = findViewById<com.github.mikephil.charting.charts.PieChart>(R.id.piechartDobles)
        val entries = ArrayList<PieEntry>()

        val factorConversio = (0.002 * 233.0) / 60.0

        for ((pestanya, minuts) in tempsPestanya) {
            if (minuts > 0) {
                val gCo2Pestanya = minuts * factorConversio
                entries.add(PieEntry(gCo2Pestanya.toFloat(), pestanya))
            }
        }

        if (entries.isEmpty()) {
            entries.add(PieEntry(1f, "Sense dades"))
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = listOf(
            Color.rgb(155, 201, 255),
            Color.rgb(255, 193, 155),
            Color.rgb(155, 255, 193),
            Color.rgb(212, 155, 255)
        )
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val data = PieData(dataSet)
        pieChart.data = data

        pieChart.description.isEnabled = false
        pieChart.centerText = "CO2 per Secció (g)"
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.animateY(1000)
        pieChart.invalidate()
    }
}
