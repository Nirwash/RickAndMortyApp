package com.nirwashh.rickandmortyapp.episodes.domain

class EpisodesInteractor(private val repository: EpisodesRepository) {

    fun getEpisodes(
        name: String?,
        episode: String?
    ) =
        repository.getEpisodes(name, episode)

    suspend fun getEpisodesByIds(ids: String) =
        repository.getEpisodesByIds(ids)

    fun getObservableEpisodesByIds(ids: List<Int>) = repository.getObservableEpisodesByIds(ids)
}