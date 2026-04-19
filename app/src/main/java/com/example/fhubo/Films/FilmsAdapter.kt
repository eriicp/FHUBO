package com.example.fhubo.Films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class FilmsAdapter(
    private val items: List<Films>,
    private val onItemClick: (Films) -> Unit
) : RecyclerView.Adapter<FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerfilmlocation, parent, false)
        return FilmsViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}