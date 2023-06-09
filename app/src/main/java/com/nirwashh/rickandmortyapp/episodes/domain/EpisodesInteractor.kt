package com.nirwashh.rickandmortyapp.episodes.domain

class EpisodesInteractor(private val repository: EpisodesRepository) {

    fun getEpisodes(
        name: String?,
        episode: String?
    ) =
        repository.getEpisodes(name, episode)

    fun getObservableEpisodesByIds(ids: List<Int>) = repository.getObservableEpisodesByIds(ids)
}