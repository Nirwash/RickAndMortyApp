package com.nirwashh.rickandmortyapp.core.di

import android.app.Application
import com.nirwashh.rickandmortyapp.characters.di.CharacterModule
import com.nirwashh.rickandmortyapp.episodes.di.EpisodeModule
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule::class, CharacterModule::class, EpisodeModule::class])
class AppModule(private val app: Application) {

    @Provides
    fun provideApp() = app
}