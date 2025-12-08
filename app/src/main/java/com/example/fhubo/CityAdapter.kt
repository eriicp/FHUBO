package com.example.fhubo

// Archivo: CityAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// El Adapter recibe una lista de objetos 'City'
class CityAdapter(private var cities: List<City>) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCityBackground: ImageView = itemView.findViewById(R.id.ivCityBackground)
        val tvCityName: TextView = itemView.findViewById(R.id.tvCityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recylercity, parent, false)
        return CityViewHolder(view)
    }

    /**
     * Se llama para conectar ("bind") los datos de una ciudad específica con las vistas de un ViewHolder.
     * Esto se ejecuta para cada elemento visible y cuando haces scroll.
     */
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]

        // Asignamos los datos a las vistas
        holder.tvCityName.text = city.name
        holder.ivCityBackground.setImageResource(city.imageResource)

        holder.itemView.setOnClickListener {
            // Aquí puedes definir qué pasa cuando se pulsa una ciudad
            // Por ejemplo, navegar a una pantalla de detalles de esa ciudad.
            // Toast.makeText(holder.itemView.context, "Has pulsado en ${city.name}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Devuelve el número total de elementos que hay en la lista.
     * El RecyclerView lo necesita para saber cuánto puede hacer scroll.
     */
    override fun getItemCount(): Int {
        return cities.size
    }

    /**
     * Una función muy útil para actualizar la lista de ciudades que muestra el adapter.
     * La usaremos para la funcionalidad de búsqueda del SearchView.
     */
    fun updateList(newList: List<City>) {
        cities = newList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado y debe redibujarse.
    }
}