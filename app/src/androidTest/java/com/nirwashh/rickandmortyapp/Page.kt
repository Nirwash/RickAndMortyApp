package com.nirwashh.rickandmortyapp

sealed class Page {
    class CharactersListPage : Page() {
        val recyclerView = R.id.rvCharacters
    }

    class CharacterDetailsPage : Page() {
        val characterName = R.id.tvTitle
        val buttonBack = R.id.btn_back_detail
    }

    class EpisodeListPage : Page() {
        val recyclerView = com.nirwashh.rickandmortyapp.R.id.rvEpisodes
    }

    class LocationListPage : Page() {
        val recyclerView = com.nirwashh.rickandmortyapp.R.id.rvLocations
    }

    class EpisodeDetailsPage : Page() {
        val characterName = R.id.tvTitle
        val buttonBack = R.id.btn_back_episode_detail
    }

    class LocationDetailsPage : Page() {
        val characterName = R.id.tvTitle
        val buttonBack = R.id.btn_back_location_detail
    }

    class MainActivity : Page() {
        val bottomNavBar = R.id.bottom_nav_view
        val characterPage = R.id.mCharacters
        val episodePage = R.id.mEpisodes
        val locationPage = R.id.mLocations
    }
}