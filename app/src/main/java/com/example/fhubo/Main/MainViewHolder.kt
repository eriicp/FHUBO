package com.example.fhubo.Main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class MainViewHolder(
    itemView: View,
    private val onItemClick: (Main) -> Unit,
    private val onThreeDotsClick: (Main) -> Unit // 1. Añadimos el nuevo listener
) : RecyclerView.ViewHolder(itemView) {

    private val ivFilm : ImageView = itemView.findViewById(R.id.ivFilmName)
    // 2. Buscamos el botón de los tres puntos (asumiendo el ID 'ivMore')

    fun bind(item: Main) {
        ivFilm.setImageResource(item.imageResource)

        // Asignamos el listener para el clic en todo el elemento
        itemView.setOnClickListener {
            onItemClick(item)
        }

        // 3. Asignamos el listener para el clic en los tres puntos

    }
}
