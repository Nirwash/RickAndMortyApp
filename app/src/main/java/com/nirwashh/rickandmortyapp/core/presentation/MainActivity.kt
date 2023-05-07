package com.nirwashh.rickandmortyapp.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.nirwashh.rickandmortyapp.R
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.presentation.fragments.CharacterDetailsFragment
import com.nirwashh.rickandmortyapp.characters.presentation.fragments.CharactersFragment
import com.nirwashh.rickandmortyapp.databinding.ActivityMainBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.presentation.fragments.EpisodeDetailsFragment
import com.nirwashh.rickandmortyapp.episodes.presentation.fragments.EpisodesFragment
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.presentation.fragments.LocationDetailsFragment
import com.nirwashh.rickandmortyapp.locations.presentation.fragments.LocationsFragment

class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        val charactersFragment = CharactersFragment()
        val locationsFragment = LocationsFragment()
        val episodesFragment = EpisodesFragment()
        setCurrentFragment(charactersFragment, false)

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mCharacters -> setCurrentFragment(charactersFragment, false)
                R.id.mLocations -> setCurrentFragment(locationsFragment, false)
                R.id.mEpisodes -> setCurrentFragment(episodesFragment, false)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment, addBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            if (addBackStack)
                addToBackStack(fragment.tag)
            else
                supportFragmentManager.popBackStack()
            commit()
        }
    }

    override fun navigateToCharacterDetails(character: Character) {
        setCurrentFragment(CharacterDetailsFragment.newInstance(character), true)
    }

    override fun navigateToEpisodeDetails(episode: Episode) {
        setCurrentFragment(EpisodeDetailsFragment.newInstance(episode), true)
    }

    override fun navigateToLocationDetails(location: Location) {
        setCurrentFragment(LocationDetailsFragment.newInstance(location), true)
    }
}