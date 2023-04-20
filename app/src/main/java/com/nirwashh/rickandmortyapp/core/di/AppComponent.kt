package com.nirwashh.rickandmortyapp.core.di

import com.nirwashh.rickandmortyapp.characters.presentation.fragments.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(charactersFragment: CharactersFragment)
}