package com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi

class EpisodeDetailViewModelFactory(
    private val charactersInteractor: CharactersInteractor,
    private val characterDomainToUi: CharacterDomainToUi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(charactersInteractor, characterDomainToUi) as T
    }
}