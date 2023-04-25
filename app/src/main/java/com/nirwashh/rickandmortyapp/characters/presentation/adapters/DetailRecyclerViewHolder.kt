package com.nirwashh.rickandmortyapp.characters.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem.CharacterViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem.EpisodeViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem.LocationViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem.OriginViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem.TitleViewItem
import com.nirwashh.rickandmortyapp.databinding.ItemCharacterDetailBinding
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeDetailBinding
import com.nirwashh.rickandmortyapp.databinding.ItemLocationBinding
import com.nirwashh.rickandmortyapp.databinding.ItemOriginBinding
import com.nirwashh.rickandmortyapp.databinding.ItemTitleBinding

sealed class DetailRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    class CharacterViewHolder(private val binding: ItemCharacterDetailBinding) :
        DetailRecyclerViewHolder(binding) {
        fun bind(character: CharacterViewItem) {
            with(binding) {
                Glide.with(itemView).load(character.image).into(imageCharacter)
                created.text = character.created
                status.text = character.status
                gender.text = character.gender
                species.text = character.species
                type.text = character.type
                setVisibility(species, character.species == "")
                setVisibility(tvSpecies, character.species == "")
                setVisibility(type, character.type == "")
                setVisibility(tvType, character.type == "")
            }
        }

        private fun setVisibility(view: View, shouldNotVisible: Boolean) {
            if (shouldNotVisible)
                view.visibility = View.INVISIBLE
            else
                view.visibility = View.VISIBLE
        }
    }

    class LocationViewHolder(private val binding: ItemLocationBinding) :
        DetailRecyclerViewHolder(binding) {
        fun bind(location: LocationViewItem, listener: CharacterDetailsAdapter.Listener) {
            with(binding) {
                tvLocationName.text = location.name
            }
            itemView.setOnClickListener {
                listener.onClick(location)
            }
        }
    }

    class OriginViewHolder(private val binding: ItemOriginBinding) :
        DetailRecyclerViewHolder(binding) {
        fun bind(origin: OriginViewItem, listener: CharacterDetailsAdapter.Listener) {
            with(binding) {
                tvOriginName.text = origin.name
            }
            itemView.setOnClickListener {
                listener.onClick(origin)
            }
        }
    }

    class TitleViewHolder(private val binding: ItemTitleBinding) :
        DetailRecyclerViewHolder(binding) {
        fun bind(title: TitleViewItem) {
            with(binding) {
                tvTitle.text = title.title
            }
        }
    }

    class EpisodeViewHolder(private val binding: ItemEpisodeDetailBinding) :
        DetailRecyclerViewHolder(binding) {
        fun bind(episode: EpisodeViewItem, listener: CharacterDetailsAdapter.Listener) {
            with(binding) {
                tvEpisodeName.text = episode.name
                tvEpisode.text = episode.episode
            }
            itemView.setOnClickListener {
                listener.onClick(episode)
            }
        }
    }

}