package com.nirwashh.rickandmortyapp.episodes.di

import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesRepository
import dagger.Module
import dagger.Provides

@Module
class EpisodeDomainModule {

    @Provides
    fun provideEpisodesInteractor(episodesRepository: EpisodesRepository) =
        EpisodesInteractor(episodesRepository)
}