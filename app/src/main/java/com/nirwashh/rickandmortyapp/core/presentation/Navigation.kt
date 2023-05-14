package com.nirwashh.rickandmortyapp.core.presentation

import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi

interface Navigation {
    fun navigateToCharacterDetails(character: CharacterUi)
    fun navigateToEpisodeDetails(episode: EpisodeUi)
    fun navigateToLocationDetails(location: LocationUi)
}