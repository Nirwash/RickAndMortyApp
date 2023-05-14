package com.nirwashh.rickandmortyapp.episodes.domain.model

data class EpisodeDomain(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)