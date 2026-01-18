package com.example.fhubo.Favorites

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class FavoritesViewHolder (itemView: View,
                           private val onItemClick: (Favorites) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvFavorite: TextView = itemView.findViewById(R.id.tvFilmUbicacio)
    private val ivFavorite: ImageView = itemView.findViewById(R.id.ivCityBackground)

    fun bind(item: Favorites) {
        tvFavorite.text = item.name
        ivFavorite.setBackgroundResource(item.imageResource)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}