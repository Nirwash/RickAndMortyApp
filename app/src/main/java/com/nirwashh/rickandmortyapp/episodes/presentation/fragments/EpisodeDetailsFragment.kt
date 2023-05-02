package com.nirwashh.rickandmortyapp.episodes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodeDetailsBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode

class EpisodeDetailsFragment : Fragment() {
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation
    private lateinit var episode: Episode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        episode = arguments?.getParcelable(EPISODE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EPISODE = "Episode"
        fun newInstance(episode: Episode): Fragment {
            val fragment = EpisodeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EPISODE, episode)
                }
            }
            return fragment
        }
    }
}