package com.nirwashh.rickandmortyapp.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nirwashh.rickandmortyapp.R
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.presentation.detail.fragments.CharacterDetailsFragment
import com.nirwashh.rickandmortyapp.characters.presentation.list.fragments.CharactersFragment
import com.nirwashh.rickandmortyapp.databinding.ActivityMainBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.presentation.detail.fragments.EpisodeDetailsFragment
import com.nirwashh.rickandmortyapp.episodes.presentation.list.fragments.EpisodesFragment
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.presentation.detail.fragments.LocationDetailsFragment
import com.nirwashh.rickandmortyapp.locations.presentation.list.fragments.LocationsFragment

class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding
    private lateinit var charactersFragment: CharactersFragment
    private lateinit var locationsFragment: LocationsFragment
    private lateinit var episodesFragment: EpisodesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        charactersFragment = CharactersFragment()
        locationsFragment = LocationsFragment()
        episodesFragment = EpisodesFragment()
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
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
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