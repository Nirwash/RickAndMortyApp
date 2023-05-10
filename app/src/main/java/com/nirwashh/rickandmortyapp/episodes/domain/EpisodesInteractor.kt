package com.nirwashh.rickandmortyapp.episodes.domain

import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters

class EpisodesInteractor(private val repository: EpisodesRepository) {

    suspend fun getEpisodes(filters: EpisodeFilters) =
        repository.getEpisodes(filters)

    suspend fun getEpisodesByIds(ids: String) =
        repository.getEpisodesByIds(ids)

    fun getObservableEpisodesByIds(ids: String) = repository.getObservableEpisodesByIds(ids)
}