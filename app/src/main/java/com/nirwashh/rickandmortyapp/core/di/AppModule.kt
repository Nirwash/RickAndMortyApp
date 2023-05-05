package com.nirwashh.rickandmortyapp.core.di

import com.nirwashh.rickandmortyapp.characters.di.CharacterModule
import com.nirwashh.rickandmortyapp.episodes.di.EpisodeModule
import com.nirwashh.rickandmortyapp.locations.di.LocationModule
import dagger.Module


@Module(includes = [NetworkModule::class, RoomModule::class, CharacterModule::class, EpisodeModule::class, LocationModule::class])
class AppModule