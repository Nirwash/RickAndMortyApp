package com.nirwashh.rickandmortyapp.locations.presentation.mapper

import com.nirwashh.rickandmortyapp.core.utils.Mapper
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi
import javax.inject.Inject

class LocationDomainToUi @Inject constructor() : Mapper<LocationUi, LocationDomain> {
    override fun map(source: LocationDomain) = LocationUi(
        created = source.created,
        dimension = source.dimension,
        id = source.id,
        name = source.name,
        residents = source.residents,
        type = source.type,
        url = source.url
    )
}