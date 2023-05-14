package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EpisodeDomainToUiMapperTest {
    private lateinit var episodeUi: EpisodeUi
    private lateinit var episodeDomain: EpisodeDomain

    @Test
    fun `should return episode ui`() {
        val expected = episodeUi
        val actual = EpisodeDomainToUi().map(episodeDomain)
        Assert.assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        episodeUi = EpisodeUi(
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