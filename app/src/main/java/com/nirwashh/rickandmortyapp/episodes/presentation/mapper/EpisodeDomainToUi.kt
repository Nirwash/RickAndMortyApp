package com.nirwashh.rickandmortyapp.episodes.presentation.mapper

import com.nirwashh.rickandmortyapp.core.utils.Mapper
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi
import javax.inject.Inject

class EpisodeDomainToUi @Inject constructor() : Mapper<EpisodeUi, EpisodeDomain> {
    override fun map(source: EpisodeDomain) = EpisodeUi(
        air_date = source.air_date,
        characters = source.characters,
        created = source.created,
        episode = source.episode,
        id = source.id,
        name = source.name,
        url = source.url
    )
}