package com.example.fhubo.Main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fhubo.R

class MainViewHolder(
    itemView: View,
    private val onItemClick: (Main) -> Unit,
    private val onThreeDotsClick: (Main) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val ivFilm: ImageView = itemView.findViewById(R.id.ivFilmName)
    private val btnThreeDots: ImageView = itemView.findViewById(R.id.recycler3puntos)

    fun bind(item: Main) {
        // Use Glide to load the image from the URL provided in imagePath
        Glide.with(itemView.context)
            .load(item.imagePath)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.logo)
            .into(ivFilm)

        itemView.setOnClickListener {
            onItemClick(item)
        }
        
        btnThreeDots.setOnClickListener {
            onThreeDotsClick(item)
        }
    }
}
