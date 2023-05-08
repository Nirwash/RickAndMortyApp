package com.nirwashh.rickandmortyapp.locations.presentation.detail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nirwashh.rickandmortyapp.R;
import com.nirwashh.rickandmortyapp.characters.data.model.Character;

import java.util.ArrayList;

public class LocationDetailAdapter extends RecyclerView.Adapter<LocationDetailAdapter.LocationDetailViewHolder> {
    public final ArrayList<Character> characters;
    private final Listener listener;

    public static class LocationDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView imgCharacter;

        public LocationDetailViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvCharacterDetailName);
            imgCharacter = (ImageView) itemView.findViewById(R.id.imgCharacterDetail);
        }
    }

    public LocationDetailAdapter(ArrayList<Character> characters, Listener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LocationDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View characterView = inflater.inflate(R.layout.item_character_detail, parent, false);
        return new LocationDetailViewHolder(characterView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationDetailViewHolder holder, int position) {
        Character character = characters.get(position);
        TextView textView = holder.tvName;
        ImageView imageView = holder.imgCharacter;
        View itemView = holder.itemView;
        textView.setText(character.getName());
        Glide.with(itemView).load(character.getImage()).into(imageView);
        holder.itemView.setOnClickListener(view -> listener.onClick(character.getId()));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


    public interface Listener {
        void onClick(int characterID);
    }


}
