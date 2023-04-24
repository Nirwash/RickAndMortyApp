package com.nirwashh.rickandmortyapp.episodes.domain

import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import javax.inject.Inject

class EpisodesInteractor @Inject constructor(private val repository: EpisodesRepository) {

    suspend fun getEpisodes(filters: EpisodeFilters) =
        repository.getEpisodes(filters)

    suspend fun getEpisodes(ids: String) =
        repository.getEpisodes(ids)

    suspend fun getEpisode(id: Int) = repository.getEpisode(id)
}