package com.nirwashh.rickandmortyapp.characters.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterBinding

class CharactersAdapter(private val listener: Listener) :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = checkNotNull(getItem(position))
        with(holder) {
            with(binding) {
                Glide.with(itemView).load(character.image).into(imgCharacter)
                tvName.text = character.name
                species.text = character.species
                status.text = character.status
                gender.text = character.gender
            }
            itemView.setOnClickListener {
                listener.onClick(character)
            }
        }
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

    interface Listener {
        fun onClick(character: Character)
    }
}



