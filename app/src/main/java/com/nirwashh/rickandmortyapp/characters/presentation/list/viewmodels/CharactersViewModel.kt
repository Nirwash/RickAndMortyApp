package com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.list.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class CharactersViewModel(
    private val interactor: CharactersInteractor,
    private val characterMapper: CharacterDomainToUi
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

//    fun load(
//        name: String?,
//        status: String?,
//        gender: String?,
//        type: String?,
//        species: String?
//    ) {
//        charactersFlow = interactor.getCharacters(
//            name = name,
//            status = status,
//            gender = gender,
//            type = type,
//            species = species
//        ).map { pagingData ->
//            pagingData.map { character ->
//                characterMapper.map(character)
//            }
//        }.cachedIn(viewModelScope)
//            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
//    }

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
                it.map { character -> characterMapper.map(character) }
            )
        }.launchIn(viewModelScope)
    }
}