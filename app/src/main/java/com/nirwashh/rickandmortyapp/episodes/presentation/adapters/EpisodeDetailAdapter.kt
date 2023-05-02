package com.nirwashh.rickandmortyapp.episodes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterDetailBinding

class EpisodeDetailAdapter(
    val characters: MutableList<Character> = mutableListOf(),
    private val listener: Listener
) : RecyclerView.Adapter<EpisodeDetailAdapter.EpisodeDetailViewHolder>() {
    class EpisodeDetailViewHolder(val binding: ItemCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeDetailViewHolder(
            ItemCharacterDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: EpisodeDetailViewHolder, position: Int) {
        val character = characters[position]
        with(holder) {
            with(binding) {
                Glide.with(itemView).load(character.image).into(imgCharacter)
                tvName.text = character.name
            }
            itemView.setOnClickListener {
                listener.onClick(character)
            }
        }
    }
}
