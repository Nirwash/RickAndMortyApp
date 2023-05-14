package com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val characterInteractor: CharactersInteractor,
    private val characterDomainToUi: CharacterDomainToUi
) : ViewModel() {

    var characters = MutableLiveData<List<CharacterUi>>()

    fun setCharacters(ids: String) {
        viewModelScope.launch {
            characters.value =
                characterInteractor.getCharactersByIds(ids).map { characterDomainToUi.map(it) }
        }
    }

    fun getCharacter(id: Int): CharacterUi {
        return characters.value?.find { it.id == id }!!
    }

}