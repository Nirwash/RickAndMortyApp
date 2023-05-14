package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor;
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi;

public class LocationDetailViewModelFactory implements ViewModelProvider.Factory {
    CharactersInteractor charactersInteractor;
    CharacterDomainToUi characterDomainToUi;

    public LocationDetailViewModelFactory(CharactersInteractor interactor, CharacterDomainToUi mapper) {
        this.charactersInteractor = interactor;
        this.characterDomainToUi = mapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LocationDetailViewModel(charactersInteractor, characterDomainToUi);
    }
}
