package com.nirwashh.rickandmortyapp.core.presentation

import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode

interface Navigation {
    fun navigateToCharacterDetails(character: Character)
    fun navigateToEpisodeDetails(episode: Episode)
    fun navigateToLocationDetails()
}