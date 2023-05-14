package com.nirwashh.rickandmortyapp.episodes.presentation.list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.core.utils.setWidthPercent
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodeFilterBinding
import com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels.EpisodesViewModel

class EpisodeFiltersFragment(private val viewModel: EpisodesViewModel) : DialogFragment() {
    private lateinit var binding: FragmentEpisodeFilterBinding
    private lateinit var refresh: RefreshCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh = parentFragment as RefreshCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        init()
        with(binding) {
            clearButton.setOnClickListener {
                clearFilters()
            }
            applyButton.setOnClickListener {
                applyFilters()
            }
        }
    }

    private fun init() {
        with(binding) {
            search.setText(viewModel.filters.value.getValue("name"))
            episode.setText(viewModel.filters.value.getValue("episode"))
        }
    }

    private fun applyFilters() {
        val name = if (binding.search.text.toString() == "")
            null
        else
            binding.search.text.toString()
        val episode = if (binding.episode.text.toString() == "")
            null
        else
            binding.episode.text.toString()
        refresh(name, episode)
        dismiss()
    }

    private fun clearFilters() {
        refresh(null, null)
        dismiss()
    }


    interface RefreshCallback {
        operator fun invoke(name: String?, episode: String?)
    }
}



