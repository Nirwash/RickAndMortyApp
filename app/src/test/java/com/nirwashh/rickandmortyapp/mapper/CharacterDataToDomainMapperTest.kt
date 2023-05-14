package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.characters.data.mapper.CharacterDataToDomain
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import com.nirwashh.rickandmortyapp.characters.data.model.Location
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDataToDomainMapperTest {
    private lateinit var characterData: CharacterData
    private lateinit var characterDomain: CharacterDomain

    @Test
    fun `should return character domain`() {
        val expected = characterDomain
        val actual = CharacterDataToDomain().map(characterData)
        assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        characterData = CharacterData(
            id = 1,
            name = "Name",
            status = "Status",
            species = "Species",
            type = "Type",
            gender = "Gender",
            image = "Image",
            origin = Location(
                "locationName",
                "locationUrl"
            ),
            location = Location(
                "locationName",
                "locationUrl"
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