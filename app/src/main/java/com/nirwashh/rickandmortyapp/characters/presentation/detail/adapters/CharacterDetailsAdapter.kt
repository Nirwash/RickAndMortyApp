package com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.rickandmortyapp.R
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailRecyclerViewHolder.CharacterViewHolder
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailRecyclerViewHolder.EpisodeViewHolder
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailRecyclerViewHolder.LocationViewHolder
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailRecyclerViewHolder.OriginViewHolder
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailRecyclerViewHolder.TitleViewHolder
import com.nirwashh.rickandmortyapp.databinding.CharacterDetailBinding
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeDetailBinding
import com.nirwashh.rickandmortyapp.databinding.ItemLocationDetailBinding
import com.nirwashh.rickandmortyapp.databinding.ItemOriginBinding
import com.nirwashh.rickandmortyapp.databinding.ItemTitleBinding

class CharacterDetailsAdapter(
    val viewItems: MutableList<DetailsRecyclerViewItem> = mutableListOf(),
    private val listener: Listener
) :
    RecyclerView.Adapter<DetailRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailRecyclerViewHolder {
        return when (viewType) {
            R.layout.character_detail -> CharacterViewHolder(
                CharacterDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_episode -> EpisodeViewHolder(
                ItemEpisodeDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_location_detail -> LocationViewHolder(
                ItemLocationDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_origin -> OriginViewHolder(
                ItemOriginBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_title -> TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType provided")
        }
    }

    override fun getItemCount() = viewItems.size

    override fun onBindViewHolder(holder: DetailRecyclerViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> holder.bind(viewItems[position] as DetailsRecyclerViewItem.CharacterViewItem)
            is EpisodeViewHolder -> holder.bind(
                viewItems[position] as DetailsRecyclerViewItem.EpisodeViewItem,
                listener
            )

            is LocationViewHolder -> holder.bind(
                viewItems[position] as DetailsRecyclerViewItem.LocationViewItem,
                listener
            )

            is OriginViewHolder -> holder.bind(
                viewItems[position] as DetailsRecyclerViewItem.OriginViewItem,
                listener
            )

            is TitleViewHolder -> holder.bind(viewItems[position] as DetailsRecyclerViewItem.TitleViewItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewItems[position]) {
            is DetailsRecyclerViewItem.CharacterViewItem -> R.layout.character_detail
            is DetailsRecyclerViewItem.EpisodeViewItem -> R.layout.item_episode
            is DetailsRecyclerViewItem.LocationViewItem -> R.layout.item_location_detail
            is DetailsRecyclerViewItem.OriginViewItem -> R.layout.item_origin
            is DetailsRecyclerViewItem.TitleViewItem -> R.layout.item_title
            else -> {
                throw IllegalStateException()
            }
        }
    }

    interface Listener {
        fun onClickEpisode(episodeViewItem: DetailsRecyclerViewItem.EpisodeViewItem)
        fun onClickLocation()
        fun onClickOrigin()
    }
}