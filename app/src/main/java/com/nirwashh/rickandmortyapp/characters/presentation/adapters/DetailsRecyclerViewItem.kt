package com.nirwashh.rickandmortyapp.characters.presentation.adapters

sealed class DetailsRecyclerViewItem {
    class CharacterViewItem(
        val created: String,
        val gender: String,
        val image: String,
        val name: String,
        val species: String,
        val status: String,
        val type: String
    ) : DetailsRecyclerViewItem()

    class LocationViewItem(
        val name: String
    ) : DetailsRecyclerViewItem()

    class OriginViewItem(
        val name: String
    ) : DetailsRecyclerViewItem()

    class TitleViewItem(
        val title: String
    ) : DetailsRecyclerViewItem()

    class EpisodeViewItem(
        val name: String,
        val episode: String
    ) : DetailsRecyclerViewItem()
}
