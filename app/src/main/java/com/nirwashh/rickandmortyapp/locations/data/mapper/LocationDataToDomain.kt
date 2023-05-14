package com.nirwashh.rickandmortyapp.locations.data.mapper

import com.nirwashh.rickandmortyapp.core.utils.Mapper
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import javax.inject.Inject

class LocationDataToDomain @Inject constructor() : Mapper<LocationDomain, LocationData> {
    override fun map(source: LocationData) = LocationDomain(
        created = source.created,
        dimension = source.dimension,
        id = source.id,
        name = source.name,
        residents = source.residents,
        type = source.type,
        url = source.url
    )
}