package com.example.fhubo.Films

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class FilmsViewHolder(itemView: View, private val onItemClick: (Films) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvFilmLocationName: TextView = itemView.findViewById(R.id.tvfilmNomUbicacio)

    private val tvFilmLocation: TextView = itemView.findViewById(R.id.tvFilmUbicacio)

    private val ivFilmLocation: ImageView = itemView.findViewById(R.id.ivFilmLocationBackground)

    fun bind(item: Films) {
        tvFilmLocationName.text = item.name
        tvFilmLocation.text = item.location
        ivFilmLocation.setBackgroundResource(item.imageResource)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}