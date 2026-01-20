package com.example.fhubo.CityLocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R


class CityLocationAdapter(
    private val items: List<CityLocation>,
    private val onItemClick: (CityLocation) -> Unit
) : RecyclerView.Adapter<CityLocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityLocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclercitylocation, parent, false)
        return CityLocationViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CityLocationViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}