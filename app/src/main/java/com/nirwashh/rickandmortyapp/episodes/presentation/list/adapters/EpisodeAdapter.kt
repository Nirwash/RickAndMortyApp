package com.nirwashh.rickandmortyapp.episodes.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeBinding
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi

class EpisodeAdapter(private val listener: Listener) :
    PagingDataAdapter<EpisodeUi, EpisodeAdapter.EpisodeViewHolder>(EpisodeDiffCallback()) {

    class EpisodeViewHolder(val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(episode: EpisodeUi)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position).let {
            val episode = it
            if (episode != null)
                with(holder) {
                    with(binding) {
                        tvEpisodeName.text = episode.name
                        tvEpisode.text = episode.episode
                        AirDate.text = episode.air_date
                    }
                    itemView.setOnClickListener {
                        listener.onClick(episode)
                    }
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

    class EpisodeDiffCallback : DiffUtil.ItemCallback<EpisodeUi>() {
        override fun areItemsTheSame(oldItem: EpisodeUi, newItem: EpisodeUi) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EpisodeUi, newItem: EpisodeUi) =
            oldItem == newItem
    }
}




