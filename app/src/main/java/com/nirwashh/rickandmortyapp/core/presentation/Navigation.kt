package com.nirwashh.rickandmortyapp.core.presentation

import com.nirwashh.rickandmortyapp.characters.data.model.Character

interface Navigation {
    fun navigateToCharacterDetails(character: Character)
    fun navigateToEpisodeDetails()
    fun navigateToLocationDetails()
}