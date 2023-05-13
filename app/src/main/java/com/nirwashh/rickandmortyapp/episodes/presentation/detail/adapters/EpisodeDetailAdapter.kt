package com.nirwashh.rickandmortyapp.episodes.presentation.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterDetailBinding

class EpisodeDetailAdapter(
    val characters: MutableList<CharacterUi> = mutableListOf(),
    private val listener: Listener
) : RecyclerView.Adapter<EpisodeDetailAdapter.EpisodeDetailViewHolder>() {
    class EpisodeDetailViewHolder(val binding: ItemCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(characterId: Int)
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
                Glide.with(itemView).load(character.image).into(imgCharacterDetail)
                tvCharacterDetailName.text = character.name
                imgCharacterDetail.setOnClickListener {
                    listener.onClick(character.id)
                }
            }
        }
    }
}
