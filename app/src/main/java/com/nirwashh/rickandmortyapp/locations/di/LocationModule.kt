package com.nirwashh.rickandmortyapp.locations.di

import dagger.Module

@Module(includes = [LocationDataModule::class, LocationDomainModule::class, LocationApiModule::class])
class LocationModule