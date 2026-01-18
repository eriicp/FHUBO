package com.example.fhubo.Main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class MainViewHolder(
    itemView: View,
    private val onItemClick: (Main) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val ivFilm : ImageView = itemView.findViewById(R.id.ivFilmName)

    fun bind(item: Main) {
        ivFilm.setBackgroundResource(item.imageResource)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}