package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nirwashh.rickandmortyapp.episodes.data.model.Episode;
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor;
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData;
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class CharacterDetailViewModel extends ViewModel {
    EpisodesInteractor episodesInteractor;
    LocationInteractor locationInteractor;
    public MutableLiveData<List<Episode>> episodesLiveData = new MutableLiveData<>();
    public MutableLiveData<LocationData> locationLiveData = new MutableLiveData<>();
    public MutableLiveData<LocationData> originLiveData = new MutableLiveData<>();

    public CharacterDetailViewModel(EpisodesInteractor episodesInteractor, LocationInteractor locationInteractor) {
        this.episodesInteractor = episodesInteractor;
        this.locationInteractor = locationInteractor;
    }

    public void setEpisodesLiveData(String ids) {
        episodesInteractor.getObservableEpisodesByIds(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableSingleObserver<List<Episode>>() {
                            @Override
                            public void onSuccess(List<Episode> episodes) {
                                episodesLiveData.setValue(episodes);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    public void setLocationLiveData(int locationId) {
        locationInteractor.getObservableLocationById(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableSingleObserver<LocationData>() {
                            @Override
                            public void onSuccess(LocationData location) {
                                locationLiveData.setValue(location);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    public void setOriginLiveData(int originId) {
        locationInteractor.getObservableLocationById(originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableSingleObserver<LocationData>() {
                            @Override
                            public void onSuccess(LocationData origin) {
                                originLiveData.setValue(origin);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }
                        }
                );
    }
}
