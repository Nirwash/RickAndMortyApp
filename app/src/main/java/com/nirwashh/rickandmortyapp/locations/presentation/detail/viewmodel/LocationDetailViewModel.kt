package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val characterInteractor: CharactersInteractor
) : ViewModel() {
    var characters = MutableLiveData<List<Character>>()

    fun setCharacters(ids: String) {
        viewModelScope.launch {
            characters.value = characterInteractor.getCharactersByIds(ids)
        }
    }

    fun getCharacter(id: Int): Character {
        return characters.value?.find { it.id == id }!!
    }

}