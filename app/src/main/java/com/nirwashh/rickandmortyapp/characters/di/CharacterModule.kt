package com.nirwashh.rickandmortyapp.characters.di

import dagger.Module

@Module(includes = [CharacterDataModule::class, CharacterDomainModule::class, CharacterApiModule::class])
class CharacterModule {
}