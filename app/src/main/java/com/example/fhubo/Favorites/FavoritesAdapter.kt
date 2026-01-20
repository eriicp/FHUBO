package com.example.fhubo.Favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class FavoritesAdapter (
    private val items: List<Favorites>,
    private val onItemClick: (Favorites) -> Unit
) : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerfavorites, parent, false)
        return FavoritesViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}