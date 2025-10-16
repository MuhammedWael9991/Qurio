package com.qurio.ui.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.databinding.ItemLastGameBinding

class LastGamesAdapter(
    private var lastGames: List<LastGamesEntity> = emptyList()
) : RecyclerView.Adapter<LastGamesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLastGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lastGame: LastGamesEntity) {
            binding.apply {
                binding.lastGameDate.text = lastGame.date
                binding.lastGameTitle.text = lastGame.category
                binding.duration.text = lastGame.duration
                binding.lastGamesPoitns.text = lastGame.points.toString()
                binding.lastGameStars.text = lastGame.stars.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastGamesAdapter.ViewHolder {
        val binding = ItemLastGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastGamesAdapter.ViewHolder, position: Int) {
        holder.bind(lastGames[position])
    }


    fun setData(newList: List<LastGamesEntity>) {
        lastGames = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = lastGames.size
}