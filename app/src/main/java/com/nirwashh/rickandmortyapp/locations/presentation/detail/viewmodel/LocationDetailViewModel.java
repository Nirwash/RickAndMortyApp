package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor;
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain;
import com.nirwashh.rickandmortyapp.characters.presentation.list.mapper.CharacterDomainToUi;
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LocationDetailViewModel extends ViewModel {
    CharactersInteractor charactersInteractor;
    public MutableLiveData<List<CharacterUi>> charactersLiveData = new MutableLiveData<>();

    @Inject
    CharacterDomainToUi characterDomainToUi;

    public LocationDetailViewModel(CharactersInteractor interactor) {
        charactersInteractor = interactor;
    }

    public void setCharacters(List<Integer> ids) {
        charactersInteractor.getObservableCharactersByIds(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableSingleObserver<List<CharacterDomain>>() {
                            @Override
                            public void onSuccess(List<CharacterDomain> characterResults) {
                                ArrayList<CharacterUi> characters = new ArrayList<>();
                                for (CharacterDomain character : characterResults) {
                                    characters.add(characterDomainToUi.map(character));
                                }
                                charactersLiveData.setValue(characters);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    public CharacterUi getCharacter(int id) {
        for (CharacterUi character : Objects.requireNonNull(charactersLiveData.getValue())) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }
}
