package com.nirwashh.rickandmortyapp.locations.data.model

import com.nirwashh.rickandmortyapp.characters.data.model.Info

data class LocationResponse(
    val info: Info,
    val results: List<LocationData>
)