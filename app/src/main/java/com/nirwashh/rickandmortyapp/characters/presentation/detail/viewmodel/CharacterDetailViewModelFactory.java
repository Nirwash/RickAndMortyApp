package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor;
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi;
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor;
import com.nirwashh.rickandmortyapp.locations.presentation.mapper.LocationDomainToUi;

public class CharacterDetailViewModelFactory implements ViewModelProvider.Factory {
    EpisodesInteractor episodesInteractor;
    LocationInteractor locationInteractor;
    EpisodeDomainToUi episodeDomainToUi;
    LocationDomainToUi locationDomainToUi;

    public CharacterDetailViewModelFactory(EpisodesInteractor episodesInteractor, LocationInteractor locationInteractor, EpisodeDomainToUi episodeDomainToUi, LocationDomainToUi locationDomainToUi) {
        this.episodesInteractor = episodesInteractor;
        this.locationInteractor = locationInteractor;
        this.episodeDomainToUi = episodeDomainToUi;
        this.locationDomainToUi = locationDomainToUi;
    }

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CharacterDetailViewModel(episodesInteractor, locationInteractor, episodeDomainToUi, locationDomainToUi);
    }
}

