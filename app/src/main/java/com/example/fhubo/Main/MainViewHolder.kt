package com.example.fhubo.Main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class MainViewHolder(
    itemView: View,
    private val onItemClick: (Main) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val ivFilm1 : ImageView = itemView.findViewById(R.id.ivFilmName)
    private val ivFilm2 : ImageView = itemView.findViewById(R.id.ivFilmName2)

    fun bind(item: Main) {
        ivFilm1.setBackgroundResource(item.imageResource1)
        ivFilm2.setBackgroundResource(item.imageResource2)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}