package com.example.fhubo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityLocationViewHolder(
    itemView: View,
    private val onItemClick: (CityLocation) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvCityLocationName: TextView = itemView.findViewById(R.id.tvfilmNomUbicacio)
    private val tvCityLocation: TextView = itemView.findViewById(R.id.tvFilmUbicacio)
    private val ivCityLocation : ImageView = itemView.findViewById(R.id.ivCityBackground)
    fun bind(item: CityLocation) {
        tvCityLocationName.text = item.name
        tvCityLocation.text = item.location
        ivCityLocation.setBackgroundResource(item.imageResource)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}