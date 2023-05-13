package com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.list.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailViewModel(
    private val characterInteractor: CharactersInteractor
) : ViewModel() {

    @Inject
    lateinit var characterDomainToUi: CharacterDomainToUi

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