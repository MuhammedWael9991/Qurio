package com.qurio.ui.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qurio.databinding.ItemGamePageBinding

class GameTypeAdapter(
    private val pages: List<GamePage>,
    private val onItemClick: (GamePage) -> Unit
) : RecyclerView.Adapter<GameTypeAdapter.GameViewHolder>() {

    inner class GameViewHolder(val binding: ItemGamePageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(page: GamePage) {
            binding.gameImage.setImageResource(page.imageRes)
            binding.root.setOnClickListener { onItemClick(page) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGamePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount() = pages.size

}