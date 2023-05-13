package com.nirwashh.rickandmortyapp.core.presentation

import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData

interface Navigation {
    fun navigateToCharacterDetails(character: CharacterUi)
    fun navigateToEpisodeDetails(episode: Episode)
    fun navigateToLocationDetails(location: LocationData)
}