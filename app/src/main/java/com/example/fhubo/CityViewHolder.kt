package com.example.fhubo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityViewHolder(
    itemView: View,
    private val onItemClick: (City) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvCity: TextView = itemView.findViewById(R.id.tvCityName)
    private val ivCity: ImageView = itemView.findViewById(R.id.ivCityBackground)

    fun bind(item: City) {
        tvCity.text = item.name
        ivCity.setBackgroundResource(item.imageResource)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}