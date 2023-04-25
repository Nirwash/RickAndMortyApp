package com.nirwashh.rickandmortyapp.locations.di

import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import com.nirwashh.rickandmortyapp.locations.domain.LocationRepository
import dagger.Module
import dagger.Provides

@Module
class LocationDomainModule {

    @Provides
    fun provideLocationInteractor(repository: LocationRepository) = LocationInteractor(repository)
}