package com.nirwashh.rickandmortyapp.episodes.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels.EpisodeDetailViewModelFactory
import com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels.EpisodeViewModelFactory
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi
import dagger.Module
import dagger.Provides

@Module(includes = [EpisodeDataModule::class, EpisodeDomainModule::class, EpisodeApiModule::class])
class EpisodeModule {
    @Provides
    fun provideEpisodeDetailViewModelFactory(
        interactor: CharactersInteractor,
        characterDomainToUi: CharacterDomainToUi
    ) =
        EpisodeDetailViewModelFactory(interactor, characterDomainToUi)

    @Provides
    fun provideEpisodeViewModelFactory(
        interactor: EpisodesInteractor,
        mapper: EpisodeDomainToUi
    ) =
        EpisodeViewModelFactory(interactor, mapper)
}