package com.nirwashh.rickandmortyapp.mapper

import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import com.nirwashh.rickandmortyapp.locations.presentation.mapper.LocationDomainToUi
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocationDomainToUiMapperTest {
    private lateinit var locationUi: LocationUi
    private lateinit var locationDomain: LocationDomain

    @Test
    fun `should return location domain`() {
        val expected = locationUi
        val actual = LocationDomainToUi().map(locationDomain)
        Assert.assertEquals(expected, actual)
    }

    @Before
    fun setUp() {
        locationUi = LocationUi(
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