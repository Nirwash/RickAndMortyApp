package com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nirwashh.rickandmortyapp.R;
import com.nirwashh.rickandmortyapp.databinding.CharacterDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemEpisodeDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemLocationDetailBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemOriginBinding;
import com.nirwashh.rickandmortyapp.databinding.ItemTitleBinding;

import java.util.ArrayList;

public class CharacterDetailsAdapter extends RecyclerView.Adapter<DetailRecyclerViewHolder> {
    public ArrayList<DetailsRecyclerViewItem> viewItems;
    private final Listener listener;

    public CharacterDetailsAdapter(ArrayList<DetailsRecyclerViewItem> viewItems, Listener listener) {
        super();
        this.viewItems = viewItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DetailRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailRecyclerViewHolder holder;
        switch (viewType) {
            case R.layout.character_detail:
                holder = (DetailRecyclerViewHolder) new DetailRecyclerViewHolder.CharacterViewHolder(
                        CharacterDetailBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )

                );
                break;
            case R.layout.item_episode:
                holder = (DetailRecyclerViewHolder) new DetailRecyclerViewHolder.EpisodeViewHolder(
                        ItemEpisodeDetailBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )
                );
                break;
            case R.layout.item_location_detail:
                holder = (DetailRecyclerViewHolder) new DetailRecyclerViewHolder.LocationViewHolder(
                        ItemLocationDetailBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )
                );
                break;
            case R.layout.item_origin:
                holder = (DetailRecyclerViewHolder) new DetailRecyclerViewHolder.OriginViewHolder(
                        ItemOriginBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )
                );
                break;
            case R.layout.item_title:
                holder = (DetailRecyclerViewHolder) new DetailRecyclerViewHolder.TitleViewHolder(
                        ItemTitleBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )
                );
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRecyclerViewHolder holder, int position) {
        if (holder instanceof DetailRecyclerViewHolder.CharacterViewHolder) {
            ((DetailRecyclerViewHolder.CharacterViewHolder) holder).bind(
                    (DetailsRecyclerViewItem.CharacterViewItem) viewItems.get(position));
        } else if (holder instanceof DetailRecyclerViewHolder.EpisodeViewHolder) {
            ((DetailRecyclerViewHolder.EpisodeViewHolder) holder).bind(
                    (DetailsRecyclerViewItem.EpisodeViewItem) viewItems.get(position),
                    listener
            );
        } else if (holder instanceof DetailRecyclerViewHolder.LocationViewHolder) {
            ((DetailRecyclerViewHolder.LocationViewHolder) holder).bind(
                    (DetailsRecyclerViewItem.LocationViewItem) viewItems.get(position),
                    listener
            );
        } else if (holder instanceof DetailRecyclerViewHolder.OriginViewHolder) {
            ((DetailRecyclerViewHolder.OriginViewHolder) holder).bind(
                    (DetailsRecyclerViewItem.OriginViewItem) viewItems.get(position),
                    listener
            );
        } else if (holder instanceof DetailRecyclerViewHolder.TitleViewHolder) {
            ((DetailRecyclerViewHolder.TitleViewHolder) holder).bind(
                    (DetailsRecyclerViewItem.TitleViewItem) viewItems.get(position)
            );
        }
    }

    @Override
    public int getItemViewType(int position) {
        DetailsRecyclerViewItem viewItem = (DetailsRecyclerViewItem) this.viewItems.get(position);
        int viewTypeId;
        if (viewItem instanceof DetailsRecyclerViewItem.CharacterViewItem) {
            viewTypeId = R.layout.character_detail;
        } else if (viewItem instanceof DetailsRecyclerViewItem.EpisodeViewItem) {
            viewTypeId = R.layout.item_episode;
        } else if (viewItem instanceof DetailsRecyclerViewItem.LocationViewItem) {
            viewTypeId = R.layout.item_location_detail;
        } else if (viewItem instanceof DetailsRecyclerViewItem.OriginViewItem) {
            viewTypeId = R.layout.item_origin;
        } else {
            viewTypeId = R.layout.item_title;
        }
        return viewTypeId;
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public interface Listener {
        void onClickEpisode(DetailsRecyclerViewItem.EpisodeViewItem episodeViewItem);

        void onClickLocation();

        void onClickOrigin();
    }
}
