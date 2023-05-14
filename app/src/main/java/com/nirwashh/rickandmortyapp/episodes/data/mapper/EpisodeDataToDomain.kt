package com.nirwashh.rickandmortyapp.episodes.data.mapper

import com.nirwashh.rickandmortyapp.core.utils.Mapper
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import javax.inject.Inject

class EpisodeDataToDomain @Inject constructor() : Mapper<EpisodeDomain, EpisodeData> {
    override fun map(source: EpisodeData) = EpisodeDomain(
        air_date = source.air_date,
        characters = source.characters,
        created = source.created,
        episode = source.episode,
        id = source.id,
        name = source.name,
        url = source.url
    )
}