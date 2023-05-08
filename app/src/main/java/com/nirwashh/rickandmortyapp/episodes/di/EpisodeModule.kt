package com.nirwashh.rickandmortyapp.episodes.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels.EpisodeDetailViewModelFactory
import com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels.EpisodeViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [EpisodeDataModule::class, EpisodeDomainModule::class, EpisodeApiModule::class])
class EpisodeModule {
    @Provides
    fun provideEpisodeDetailViewModelFactory(interactor: CharactersInteractor) =
        EpisodeDetailViewModelFactory(interactor)

    @Provides
    fun provideEpisodeViewModelFactory(interactor: EpisodesInteractor) =
        EpisodeViewModelFactory(interactor)
}