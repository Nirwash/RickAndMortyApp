package com.nirwashh.rickandmortyapp.locations.di

import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class LocationApiModule {
    @Singleton
    @Provides
    fun provideLocationApi(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)
}