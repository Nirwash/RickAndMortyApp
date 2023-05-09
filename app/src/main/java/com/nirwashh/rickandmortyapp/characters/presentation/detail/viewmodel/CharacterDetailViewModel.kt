package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val episodesInteractor: EpisodesInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModel() {
    var episodes = MutableLiveData<List<Episode>>()
    var locations = MutableLiveData<List<Location>>()

    fun setEpisodes(ids: String) {
        viewModelScope.launch {
            episodes.value = episodesInteractor.getEpisodesByIds(ids)
        }
    }

    fun setLocations(location: Location, origin: Location) {
        if (location.name != "unknown") {
            viewModelScope.launch {
                locations.value = locationInteractor.getLocationByName(location.name)
            }
        }
        if (origin.name != "unknown") {
            viewModelScope.launch {
                locations.value = locationInteractor.getLocationByName(origin.name)
            }
        }

    }

    fun getEpisode(id: Int): Episode {
        return episodes.value?.find { it.id == id }!!
    }

    fun getLocation(name: String): Location {
        return locations.value?.find { it.name == name }!!
    }
}