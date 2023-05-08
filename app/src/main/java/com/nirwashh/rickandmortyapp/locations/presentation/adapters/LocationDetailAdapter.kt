package com.nirwashh.rickandmortyapp.locations.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterDetailBinding

class LocationDetailAdapter(
    val characters: MutableList<Character> = mutableListOf(),
    private val listener: Listener
) : RecyclerView.Adapter<LocationDetailAdapter.LocationDetailViewHolder>() {

    class LocationDetailViewHolder(val binding: ItemCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(characterId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LocationDetailViewHolder(
        ItemCharacterDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: LocationDetailViewHolder, position: Int) {
        val character = characters[position]
        with(holder) {
            with(binding) {
                Glide.with(itemView).load(character.image).into(imgCharacter)
                tvName.text = character.name
                imgCharacter.setOnClickListener {
                    listener.onClick(character.id)
                }
            }
        }
    }
}
