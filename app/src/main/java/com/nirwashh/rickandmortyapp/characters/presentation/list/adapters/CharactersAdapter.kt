package com.nirwashh.rickandmortyapp.characters.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterBinding

class CharactersAdapter(private val listener: Listener) :
    PagingDataAdapter<CharacterUi, CharactersAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            with(holder) {
                with(binding) {
                    Glide.with(itemView).load(character.image).into(imgCharacterDetail)
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
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterUi>() {
        override fun areItemsTheSame(oldItem: CharacterUi, newItem: CharacterUi) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterUi, newItem: CharacterUi) =
            oldItem == newItem
    }

    interface Listener {
        fun onClick(character: CharacterUi)
    }
}



