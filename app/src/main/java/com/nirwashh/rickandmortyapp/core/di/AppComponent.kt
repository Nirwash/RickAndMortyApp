package com.nirwashh.rickandmortyapp.core.di

import android.content.Context
import com.nirwashh.rickandmortyapp.characters.presentation.detail.fragments.CharacterDetailsFragment
import com.nirwashh.rickandmortyapp.characters.presentation.list.fragments.CharactersFragment
import com.nirwashh.rickandmortyapp.episodes.presentation.detail.fragments.EpisodeDetailsFragment
import com.nirwashh.rickandmortyapp.episodes.presentation.list.fragments.EpisodesFragment
import com.nirwashh.rickandmortyapp.locations.presentation.detail.fragments.LocationDetailsFragment
import com.nirwashh.rickandmortyapp.locations.presentation.list.fragments.LocationsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(charactersFragment: CharactersFragment)
    fun inject(characterDetailsFragment: CharacterDetailsFragment)
    fun inject(episodesFragment: EpisodesFragment)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
    fun inject(locationDetailsFragment: LocationDetailsFragment)

    @Component.Builder
    interface AppComponentBuilder {
        fun buildAppComp(): AppComponent

        @BindsInstance
        fun context(context: Context): AppComponentBuilder
    }
}