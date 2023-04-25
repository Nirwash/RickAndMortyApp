package com.nirwashh.rickandmortyapp.episodes.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.core.utils.setWidthPercent
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodeFilterBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import com.nirwashh.rickandmortyapp.episodes.presentation.viewmodels.EpisodesViewModel

class EpisodeFiltersFragment(private val viewModel: EpisodesViewModel) : DialogFragment() {
    private lateinit var binding: FragmentEpisodeFilterBinding
    private lateinit var refresh: RefreshCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh = parentFragment as RefreshCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        init()
        with(binding) {
            clearButton.setOnClickListener {
                viewModel.clearFilters()
                updateAndClose()
            }
            applyButton.setOnClickListener {
                applyFilters()
                updateAndClose()
            }
        }
    }

    private fun init() {
        val filters = viewModel.filterState.value
        with(binding) {
            search.setText(filters.name)
            episode.setText(filters.episode)
        }
    }

    private fun applyFilters() {
        val name = binding.search.text.toString()
        val episode = binding.episode.text.toString()
        viewModel.updateFilters(EpisodeFilters(name, episode))
    }

    private fun updateAndClose() {
        viewModel.update()
        refresh()
        dismiss()
    }

    interface RefreshCallback {
        operator fun invoke()
    }
}



