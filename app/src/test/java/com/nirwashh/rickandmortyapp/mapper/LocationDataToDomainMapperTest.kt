package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.locations.data.mapper.LocationDataToDomain
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocationDataToDomainMapperTest {
    private lateinit var locationData: LocationData
    private lateinit var locationDomain: LocationDomain

    @Test
    fun `should return location domain`() {
        val expected = locationDomain
        val actual = LocationDataToDomain().map(locationData)
        assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        locationData = LocationData(
            created = "Created",
            dimension = "Dimension",
            id = 0,
            name = "Name",
            residents = listOf("CharacterTest", "CharacterTest", "CharacterTest"),
            type = "Type",
            url = "Url"
        )
        locationDomain = LocationDomain(
            created = "Created",
            dimension = "Dimension",
            id = 0,
            name = "Name",
            residents = listOf("CharacterTest", "CharacterTest", "CharacterTest"),
            type = "Type",
            url = "Url"
        )
    }
}