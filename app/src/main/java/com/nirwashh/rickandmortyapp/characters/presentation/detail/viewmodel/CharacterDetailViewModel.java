package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor;
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain;
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi;
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi;
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor;
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain;
import com.nirwashh.rickandmortyapp.locations.presentation.mapper.LocationDomainToUi;
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CharacterDetailViewModel extends ViewModel {
    EpisodesInteractor episodesInteractor;
    LocationInteractor locationInteractor;
    EpisodeDomainToUi episodeDomainToUi;
    LocationDomainToUi locationDomainToUi;
    public MutableLiveData<List<EpisodeUi>> episodesLiveData = new MutableLiveData<>();
    public MutableLiveData<LocationUi> locationLiveData = new MutableLiveData<>();
    public MutableLiveData<LocationUi> originLiveData = new MutableLiveData<>();

    public CharacterDetailViewModel(EpisodesInteractor episodesInteractor, LocationInteractor locationInteractor, EpisodeDomainToUi episodeDomainToUi, LocationDomainToUi locationDomainToUi) {
        this.episodesInteractor = episodesInteractor;
        this.locationInteractor = locationInteractor;
        this.episodeDomainToUi = episodeDomainToUi;
        this.locationDomainToUi = locationDomainToUi;
    }

    public void setEpisodesLiveData(List<Integer> ids) {
        episodesInteractor.getObservableEpisodesByIds(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<EpisodeDomain>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<EpisodeDomain> episodeDomains) {
                        setEpisodes(episodeDomains);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void setEpisodes(List<EpisodeDomain> episodeDomains) {
        ArrayList<EpisodeUi> episodeUis = new ArrayList<>();
        for (EpisodeDomain episode : episodeDomains) {
            episodeUis.add(
                    episodeDomainToUi.map(episode)
            );
        }
        episodesLiveData.setValue(episodeUis);
    }

    public void setLocationLiveData(int locationId) {
        locationInteractor.getObservableLocationById(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationDomain>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationDomain locationDomain) {
                        locationLiveData.setValue(locationDomainToUi.map(locationDomain));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setOriginLiveData(int originId) {
        locationInteractor.getObservableLocationById(originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationDomain>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationDomain locationDomain) {
                        locationLiveData.setValue(locationDomainToUi.map(locationDomain));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
