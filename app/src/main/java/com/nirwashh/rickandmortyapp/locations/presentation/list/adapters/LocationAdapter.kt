package com.nirwashh.rickandmortyapp.locations.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.rickandmortyapp.databinding.ItemLocationBinding
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData

class LocationAdapter(private val listener: Listener) :
    PagingDataAdapter<LocationData, LocationAdapter.LocationViewHolder>(LocationDiffCallback()) {

    class LocationDiffCallback : DiffUtil.ItemCallback<LocationData>() {
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData) =
            oldItem == newItem
    }

    class LocationViewHolder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(location: LocationData)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = checkNotNull(getItem(position))
        with(holder) {
            with(binding) {
                tvLocationName.text = location.name
                tvType.text = location.type
                tvDimension.text = location.dimension
            }
            itemView.setOnClickListener {
                listener.onClick(location)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}