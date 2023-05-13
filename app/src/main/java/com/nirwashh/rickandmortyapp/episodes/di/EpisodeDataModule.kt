package com.nirwashh.rickandmortyapp.episodes.di

import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService
import com.nirwashh.rickandmortyapp.episodes.data.repository.EpisodesRepositoryImpl
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesRepository
import dagger.Module
import dagger.Provides

@Module
class EpisodeDataModule {
    @Provides
    fun provideEpisodesRepository(
        service: EpisodesService,
        database: RickAndMortyDatabase
    ): EpisodesRepository = EpisodesRepositoryImpl(service, database)
}