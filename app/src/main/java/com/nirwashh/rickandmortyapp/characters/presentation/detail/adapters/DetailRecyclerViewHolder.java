package com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.nirwashh.rickandmortyapp.databinding.CharacterDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemLocationDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemOriginBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemTitleBinding;

public abstract class DetailRecyclerViewHolder extends RecyclerView.ViewHolder {
    public DetailRecyclerViewHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
    }

    public static final class CharacterViewHolder extends DetailRecyclerViewHolder {
        private final CharacterDetailBinding binding;

        public CharacterViewHolder(CharacterDetailBinding binding) {
            super((ViewBinding) binding);
            this.binding = binding;
        }

        public void bind(DetailsRecyclerViewItem.CharacterViewItem characterViewItem) {
            Glide.with(this.itemView).load(characterViewItem.getImage()).into(binding.imageCharacter);
            binding.status.setText(characterViewItem.getStatus());
            binding.gender.setText(characterViewItem.getGender());
            binding.species.setText(characterViewItem.getSpecies());
            binding.type.setText(characterViewItem.getType());
            setVisibility(binding.species, characterViewItem.getSpecies().equals(""));
            setVisibility(binding.tvSpecies, characterViewItem.getSpecies().equals(""));
            setVisibility(binding.type, characterViewItem.getType().equals(""));
            setVisibility(binding.tvType, characterViewItem.getType().equals(""));
        }

        private void setVisibility(View view, Boolean shouldNotVisible) {
            if (shouldNotVisible) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static final class LocationViewHolder extends DetailRecyclerViewHolder {
        private final ItemLocationDetailBinding binding;

        public LocationViewHolder(ItemLocationDetailBinding binding) {
            super((ViewBinding) binding);
            this.binding = binding;
        }

        public void bind(DetailsRecyclerViewItem.LocationViewItem locationViewItem, CharacterDetailsAdapter.Listener listener) {
            binding.tvLocationName.setText(locationViewItem.getName());
            itemView.setOnClickListener(view ->
                    listener.onClickLocation());
        }
    }

    public static final class OriginViewHolder extends DetailRecyclerViewHolder {
        private final ItemOriginBinding binding;

        public OriginViewHolder(ItemOriginBinding binding) {
            super((ViewBinding) binding);
            this.binding = binding;
        }

        public void bind(DetailsRecyclerViewItem.OriginViewItem originViewItem, CharacterDetailsAdapter.Listener listener) {
            binding.tvOriginName.setText(originViewItem.getName());
            itemView.setOnClickListener(view ->
                    listener.onClickOrigin());
        }
    }

    public static final class EpisodeViewHolder extends DetailRecyclerViewHolder {
        private final ItemEpisodeDetailBinding binding;

        public EpisodeViewHolder(ItemEpisodeDetailBinding binding) {
            super((ViewBinding) binding);
            this.binding = binding;
        }

        public void bind(DetailsRecyclerViewItem.EpisodeViewItem episodeViewItem, CharacterDetailsAdapter.Listener listener) {
            binding.tvEpisodeName.setText(episodeViewItem.getName());
            binding.tvEpisode.setText(episodeViewItem.getEpisode());
            itemView.setOnClickListener(view ->
                    listener.onClickEpisode(episodeViewItem));

        }
    }

    public static final class TitleViewHolder extends DetailRecyclerViewHolder {
        private final ItemTitleBinding binding;

        public TitleViewHolder(ItemTitleBinding binding) {
            super((ViewBinding) binding);
            this.binding = binding;
        }

        public void bind(DetailsRecyclerViewItem.TitleViewItem titleViewItem) {
            binding.tvTitle.setText(titleViewItem.getTitle());
        }
    }
}
