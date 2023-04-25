package com.nirwashh.rickandmortyapp.locations.di

import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import com.nirwashh.rickandmortyapp.locations.data.repository.LocationRepositoryImpl
import com.nirwashh.rickandmortyapp.locations.domain.LocationRepository
import dagger.Module
import dagger.Provides

@Module
class LocationDataModule {
    @Provides
    fun provideLocationRepository(
        service: LocationService
    ): LocationRepository = LocationRepositoryImpl(service)
}