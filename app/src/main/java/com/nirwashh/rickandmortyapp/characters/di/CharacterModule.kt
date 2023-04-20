package com.nirwashh.rickandmortyapp.characters.di

import dagger.Module

@Module(includes = [DataModule::class, DomainModule::class, NetworkModule::class, RoomModule::class])
class CharacterModule {
}