package com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CharactersViewModel(
    private val interactor: CharactersInteractor,
    private val mapper: CharacterDomainToUi
) : ViewModel() {
    var charactersFlow = MutableSharedFlow<PagingData<CharacterUi>>()
    val filters = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "gender" to null,
            "status" to null,
            "species" to null,
            "type" to null
        )
    )

    fun load(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ) {
        interactor.getCharacters(
            name, status, gender, type, species
        ).onEach {
            charactersFlow.emit(
                it.map { character -> mapper.map(character) }
            )
        }.launchIn(viewModelScope)
    }
}