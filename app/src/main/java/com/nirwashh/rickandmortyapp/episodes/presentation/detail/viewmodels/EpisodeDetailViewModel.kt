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

    val characters = MutableLiveData<List<CharacterUi>>()

    fun setCharacters(ids: String) {
        viewModelScope.launch {
            try {
                characters.value =
                    characterInteractor.getCharactersByIds(ids).map { characterDomainToUi.map(it) }
            } catch (_: Exception) {
            }

        }
    }

    fun getCharacter(id: Int): CharacterUi {
        val character = characters.value?.find { it.id == id }
        return character ?: CharacterUi(
            emptyList(),
            "",
            0,
            "",
            mapOf(Pair("", "")),
            "",
            mapOf(Pair("", "")),
            "",
            "",
            ""
        )
    }

}