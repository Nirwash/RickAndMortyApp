package com.nirwashh.rickandmortyapp.locations.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel.LocationDetailViewModelFactory
import com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel.LocationViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [LocationDataModule::class, LocationDomainModule::class, LocationApiModule::class])
class LocationModule {
    @Provides
    fun provideLocationDetailViewModelFactory(interactor: CharactersInteractor) =
        LocationDetailViewModelFactory(interactor)

    @Provides
    fun provideLocationViewModelFactory(interactor: LocationInteractor) =
        LocationViewModelFactory(interactor)
}