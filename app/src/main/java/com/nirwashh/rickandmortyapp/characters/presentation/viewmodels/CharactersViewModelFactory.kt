package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val interactor: CharactersInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            interactor
        ) as T
    }
}
