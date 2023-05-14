package com.nirwashh.rickandmortyapp.episodes.data.model

import com.nirwashh.rickandmortyapp.characters.data.model.Info

data class EpisodesResponse(
    val info: Info,
    val results: List<EpisodeData>
)
