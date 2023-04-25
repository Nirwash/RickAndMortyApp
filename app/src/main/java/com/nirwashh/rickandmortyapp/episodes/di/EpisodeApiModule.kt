package com.nirwashh.rickandmortyapp.episodes.di

import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EpisodeApiModule {
    @Singleton
    @Provides
    fun provideEpisodeApi(retrofit: Retrofit): EpisodesService =
        retrofit.create(EpisodesService::class.java)
}