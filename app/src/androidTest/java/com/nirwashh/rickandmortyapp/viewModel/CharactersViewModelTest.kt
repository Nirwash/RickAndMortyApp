package com.nirwashh.rickandmortyapp.viewModel

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.CharacterFakeToUiMapper
import com.nirwashh.rickandmortyapp.FakeCharacter
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


@ExperimentalCoroutinesApi
class CharactersViewModelTest {
    private val interactor = mock<CharactersInteractor>()
    private lateinit var viewModel: CharactersViewModel

    private val emptyPagingData: PagingData<CharacterDomain> = PagingData.from(emptyList())
    private val fakeCharacters = listOf(
        FakeCharacter(id = 1),
        FakeCharacter(id = 2),
        FakeCharacter(id = 3),
        FakeCharacter(id = 4)
    )
    private val character = fakeCharacters.map { CharacterFakeToUiMapper().map(it) }
    private val testPagingData: PagingData<CharacterUi> = PagingData.from(character)

    @Before
    fun setUp() {
        viewModel = CharactersViewModel(interactor, CharacterDomainToUi())
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_load_empty_list_of_characters() = runTest {
        Mockito.`when`(
            interactor.getCharacters(
                name = "non-existent name",
                status = "non-existent status",
                gender = "non-existent gender",
                type = "non-existent type",
                species = "non-existent species"
            )
        ).thenReturn(flowOf(emptyPagingData))

        Assert.assertEquals(PagingData.from(emptyList()), viewModel.charactersFlow.last())
    }
}