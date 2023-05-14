package com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor;
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain;
import com.nirwashh.rickandmortyapp.characters.presentation.mapper.CharacterDomainToUi;
import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LocationDetailViewModel extends ViewModel {
    CharactersInteractor charactersInteractor;
    CharacterDomainToUi characterDomainToUi;
    public MutableLiveData<List<CharacterUi>> charactersLiveData = new MutableLiveData<>();

    public LocationDetailViewModel(CharactersInteractor interactor, CharacterDomainToUi mapper) {
        charactersInteractor = interactor;
        characterDomainToUi = mapper;

    }

    public void setCharactersLiveData(List<Integer> ids) {
        charactersInteractor.getObservableCharactersByIds(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CharacterDomain>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<CharacterDomain> characterDomains) {
                        setCharacters(characterDomains);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void setCharacters(List<CharacterDomain> characterDomains) {
        ArrayList<CharacterUi> characterUis = new ArrayList<>();
        for (CharacterDomain character : characterDomains) {
            characterUis.add(
                    characterDomainToUi.map(character)
            );
        }
        charactersLiveData.setValue(characterUis);
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
