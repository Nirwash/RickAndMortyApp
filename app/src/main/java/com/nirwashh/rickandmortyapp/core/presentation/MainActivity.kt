package com.nirwashh.rickandmortyapp.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nirwashh.rickandmortyapp.R
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.presentation.fragments.CharacterDetailsFragment
import com.nirwashh.rickandmortyapp.characters.presentation.fragments.CharactersFragment
import com.nirwashh.rickandmortyapp.databinding.ActivityMainBinding
import com.nirwashh.rickandmortyapp.episodes.presentation.EpisodeDetailsFragment
import com.nirwashh.rickandmortyapp.episodes.presentation.EpisodesFragment
import com.nirwashh.rickandmortyapp.locations.presentation.LocationDetailsFragment
import com.nirwashh.rickandmortyapp.locations.presentation.LocationsFragment

class MainActivity : FragmentActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            if (addBackStack)
                addToBackStack(fragment.tag)
            commit()
        }
    }

    override fun navigateToCharacterDetails(character: Character) {
        setCurrentFragment(CharacterDetailsFragment.newInstance(character), true)
    }

    override fun navigateToEpisodeDetails() {
        setCurrentFragment(EpisodeDetailsFragment.newInstance(), true)
    }

    override fun navigateToLocationDetails() {
        setCurrentFragment(LocationDetailsFragment.newInstance(), true)
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}