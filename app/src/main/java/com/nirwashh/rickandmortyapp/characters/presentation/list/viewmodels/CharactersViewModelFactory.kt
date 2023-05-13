package com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.list.mapper.CharacterDomainToUi

class CharactersViewModelFactory(
    private val interactor: CharactersInteractor,
    private val characterMapper: CharacterDomainToUi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            interactor, characterMapper
        ) as T
    }
}
