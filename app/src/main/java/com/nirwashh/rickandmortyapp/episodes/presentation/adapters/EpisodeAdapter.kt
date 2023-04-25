package com.nirwashh.rickandmortyapp.episodes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode

class EpisodeAdapter(private val listener: Listener) :
    PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(EpisodeDiffCallback()) {

    class EpisodeViewHolder(val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(episode: Episode)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = checkNotNull(getItem(position))
        with(holder) {
            with(binding) {
                tvEpisodeName.text = episode.name
                tvEpisode.text = episode.episode
                AirDate.text = episode.air_date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem == newItem
    }
}




