package com.nirwashh.rickandmortyapp.episodes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            navigation.navigateToEpisodeDetails()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

