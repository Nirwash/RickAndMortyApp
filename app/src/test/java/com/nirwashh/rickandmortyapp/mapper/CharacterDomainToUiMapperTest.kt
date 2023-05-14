package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi
import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterDomainToUiMapperTest {
    private lateinit var characterUi: CharacterUi
    private lateinit var characterDomain: CharacterDomain

    @Test
    fun `should return character ui`() {
        val expected = characterUi
        val actual = CharacterDomainToUi().map(characterDomain)
        Assert.assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        characterUi = CharacterUi(
            id = 1,
            name = "Name",
            status = "Status",
            species = "Species",
            type = "Type",
            gender = "Gender",
            image = "Image",
            origin = mapOf(
                Pair("locationName", "locationName"),
                Pair("locationId", "locationUrl")
            ),
            location = mapOf(
                Pair("locationName", "locationName"),
                Pair("locationId", "locationUrl")
            ),
            episode = listOf(
                "episode1",
                "episode2",
                "episode3"
            )
        )
        characterDomain = CharacterDomain(
            id = 1,
            name = "Name",
            status = "Status",
            species = "Species",
            type = "Type",
            gender = "Gender",
            image = "Image",
            origin = mapOf(
                Pair("locationName", "locationName"),
                Pair("locationId", "locationUrl")
            ),
            location = mapOf(
                Pair("locationName", "locationName"),
                Pair("locationId", "locationUrl")
            ),
            episode = listOf(
                "episode1",
                "episode2",
                "episode3"
            )
        )
    }
}