package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val app: Application,
    private val interactor: CharactersInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            app,
            interactor
        ) as T
    }
}
