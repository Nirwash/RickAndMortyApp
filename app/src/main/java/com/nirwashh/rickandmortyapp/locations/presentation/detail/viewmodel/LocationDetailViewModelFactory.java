package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor;

public class LocationDetailViewModelFactory implements ViewModelProvider.Factory {
    CharactersInteractor charactersInteractor;

    public LocationDetailViewModelFactory(CharactersInteractor interactor) {
        this.charactersInteractor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LocationDetailViewModel(charactersInteractor);
    }
}
