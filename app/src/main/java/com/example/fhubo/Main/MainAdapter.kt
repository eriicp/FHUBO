package com.example.fhubo.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fhubo.R

class MainAdapter(
    private var items: List<Main>,
    private val onItemClick: (Main) -> Unit,
    private val onThreeDotsClick: (Main) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerfilm, parent, false)
        return MainViewHolder(view, onItemClick, onThreeDotsClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateList(newList: List<Main>) {
        items = newList
        notifyDataSetChanged()
    }
}
