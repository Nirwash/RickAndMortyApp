package com.nirwashh.rickandmortyapp.characters.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel.CharacterDetailViewModelFactory
import com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels.CharactersViewModelFactory
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import dagger.Module
import dagger.Provides

@Module(includes = [CharacterDataModule::class, CharacterDomainModule::class, CharacterApiModule::class])
class CharacterModule {
    @Provides
    fun provideCharacterDetailViewModelFactory(
        episodeInteractor: EpisodesInteractor,
        locationInteractor: LocationInteractor,
        episodeDomainToUi: EpisodeDomainToUi
    ) =
        CharacterDetailViewModelFactory(episodeInteractor, locationInteractor, episodeDomainToUi)

    @Provides
    fun provideCharactersViewModelFactory(
        interactor: CharactersInteractor,
        characterMapper: CharacterDomainToUi
    ) =
        CharactersViewModelFactory(interactor, characterMapper)
}