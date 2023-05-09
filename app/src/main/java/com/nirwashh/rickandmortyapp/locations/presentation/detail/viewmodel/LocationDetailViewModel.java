package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nirwashh.rickandmortyapp.characters.data.model.Character;
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LocationDetailViewModel extends ViewModel {
    CharactersInteractor charactersInteractor;
    public MutableLiveData<List<Character>> charactersLiveData = new MutableLiveData<>();

    public LocationDetailViewModel(CharactersInteractor interactor) {
        charactersInteractor = interactor;
    }

    public void setCharacters(String ids) {
        charactersInteractor.getObservableCharactersByIds(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableSingleObserver<List<Character>>() {
                            @Override
                            public void onSuccess(List<Character> characterResults) {
                                charactersLiveData.setValue(characterResults);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    public Character getCharacter(int id) {
        for (Character character : Objects.requireNonNull(charactersLiveData.getValue())) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }
}
