package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.episodes.data.mapper.EpisodeDataToDomain
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EpisodeDataToDomainMapperTest {
    private lateinit var episodeData: EpisodeData
    private lateinit var episodeDomain: EpisodeDomain

    @Test
    fun `should return episode domain`() {
        val expected = episodeDomain
        val actual = EpisodeDataToDomain().map(episodeData)
        Assert.assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        episodeData = EpisodeData(
            air_date = "00/00/0000",
            characters = listOf("CharacterTest", "CharacterTest", "CharacterTest"),
            created = "created",
            episode = "episode",
            id = 0,
            name = "name",
            url = "url"
        )
        episodeDomain = EpisodeDomain(
            air_date = "00/00/0000",
            characters = listOf("CharacterTest", "CharacterTest", "CharacterTest"),
            created = "created",
            episode = "episode",
            id = 0,
            name = "name",
            url = "url"
        )
    }

}

