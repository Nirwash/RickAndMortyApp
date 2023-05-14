package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor;
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi;
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor;

public class CharacterDetailViewModelFactory implements ViewModelProvider.Factory {
    EpisodesInteractor episodesInteractor;
    LocationInteractor locationInteractor;
    EpisodeDomainToUi episodeDomainToUi;

    public CharacterDetailViewModelFactory(EpisodesInteractor episodesInteractor, LocationInteractor locationInteractor, EpisodeDomainToUi episodeDomainToUi) {
        this.episodesInteractor = episodesInteractor;
        this.locationInteractor = locationInteractor;
        this.episodeDomainToUi = episodeDomainToUi;
    }

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CharacterDetailViewModel(episodesInteractor, locationInteractor, episodeDomainToUi);
    }
}

