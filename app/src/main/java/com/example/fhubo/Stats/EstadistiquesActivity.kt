package com.example.fhubo.Stats

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import com.example.fhubo.BaseActivity
import com.example.fhubo.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class EstadistiquesActivity : BaseActivity() {

    private val vmodel: EstadistiquesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadistiques)

        val idUsuari = "usuariTest"
        vmodel.carregarDades(idUsuari)

        vmodel.estadistiques.observe(this) { estadistiques ->
            FhuboStatsProvider.registrarVisita("EstadistiquesActivity")

            mostrarDadesEnergia(estadistiques.minutsUsTotal)
            dibuixarGraficActivities(estadistiques.vistesPerActivity)
            dibuixarGraficSeccions(estadistiques.tempsPerPestanya)
            dibuixarGraficPelicules(estadistiques.peliculesPerCategoria)
        }

        // Configurar el botó de borrar
        val btnBorrar = findViewById<ImageButton>(R.id.btn_borrar_dades)
        btnBorrar.setOnClickListener {
            FhuboStatsProvider.borrarDades()
            // Forcem el guardat buit a Firebase immediatament
            vmodel.guardarDades(idUsuari)
            // Tornem a carregar per refrescar la UI (ara estarà buida)
            vmodel.carregarDades(idUsuari)
        }
        
        // Botó per tancar (opcional, si el toolbar navigation no està configurat)
        findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar_estadistiques)
            .setNavigationOnClickListener { finish() }
    }

    private fun mostrarDadesEnergia(minuts: Float) {
        val hores = minuts / 60.0
        val energiaKWh = hores * 0.002
        val kgCo2 = energiaKWh * 0.233

        val textCo2 = findViewById<TextView>(R.id.txtEnergiaCo2)
        textCo2.text = "Temps d'ús: ${hores.format(2)} hores\n" +
                "CO2 generat: ${kgCo2.format(2)} kg"
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
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

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
            entries.add(PieEntry(1f, "Sense dades"))
        }

        val dataSet = PieDataSet(entries, "Gèneres")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.centerText = "Pel·lícules\nper Gènere"
        pieChart.setCenterTextSize(14f)
        pieChart.description.isEnabled = false
        pieChart.animateY(1000)
        pieChart.invalidate()
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun dibuixarGraficSeccions(tempsPestanya: HashMap<String, Float>) {
        val barChart = findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.barChartSeccions)
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        var index = 0f
        for ((pestanya, minuts) in tempsPestanya) {
            if (minuts > 0) {
                entries.add(BarEntry(index, minuts))
                labels.add(pestanya)
                index++
            }
        }

        if (entries.isEmpty()) {
            entries.add(BarEntry(0f, 1f))
            labels.add("Sense dades")
        }

        val dataSet = BarDataSet(entries, "Minuts")
        dataSet.colors = ColorTemplate.LIBERTY_COLORS.toList()
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val data = BarData(dataSet)
        barChart.data = data

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setGranularity(1f)
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.invalidate()
    }
}
