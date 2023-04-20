package com.nirwashh.rickandmortyapp.core.di

import android.app.Application
import com.nirwashh.rickandmortyapp.characters.di.CharacterModule
import dagger.Module
import dagger.Provides

@Module(includes = [CharacterModule::class])
class AppModule(private val app: Application) {

    @Provides
    fun provideApp() = app
}