package com.qurio.ui.screen.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.qurio.R
import com.qurio.data.local.entity.CharactersEntity
import com.qurio.databinding.ItemCharacterBinding

class CharactersAdapter(
    private var characters: List<CharactersEntity> = emptyList()
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(characters: CharactersEntity) {
            binding.apply {
                if (characters.isOwned) {
                    val resId = itemView.context.resources.getIdentifier(characters.image, "drawable", itemView.context.packageName)
                    binding.characterAvatar.setImageResource(resId)
                }else {
                    val resId = itemView.context.resources.getIdentifier(characters.lockedImage, "drawable", itemView.context.packageName)
                    binding.characterAvatar.setImageResource(resId)
                }
                binding.characterName.text = characters.name
                if (characters.isSelected) {
                    binding.checkedIcon.visibility = View.VISIBLE
                    binding.characterName.setTextColor(ContextCompat.getColor(itemView.context, R.color.Primary))
                } else {
                    binding.checkedIcon.visibility = View.GONE
                    binding.characterName.setTextColor(ContextCompat.getColor(itemView.context, R.color.Shade_Secondary))
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        holder.bind(characters[position])
    }


    fun setData(newList: List<CharactersEntity>) {
        characters = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size
}