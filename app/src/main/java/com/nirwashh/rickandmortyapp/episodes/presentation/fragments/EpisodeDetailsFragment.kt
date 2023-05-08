package com.nirwashh.rickandmortyapp.episodes.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.core.utils.idsParser
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodeDetailsBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.presentation.adapters.EpisodeDetailAdapter
import com.nirwashh.rickandmortyapp.episodes.presentation.viewmodels.EpisodeViewModelFactory
import com.nirwashh.rickandmortyapp.episodes.presentation.viewmodels.EpisodesViewModel
import javax.inject.Inject

class EpisodeDetailsFragment : Fragment(), EpisodeDetailAdapter.Listener {
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation
    private lateinit var currentEpisode: Episode
    private lateinit var viewModel: EpisodesViewModel
    private lateinit var episodeDetailAdapter: EpisodeDetailAdapter

    @Inject
    lateinit var vmFactory: EpisodeViewModelFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        viewModel = ViewModelProvider(this, vmFactory)[EpisodesViewModel::class.java]
        currentEpisode = arguments?.getParcelable(EPISODE)!!
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
        setupEpisode()
        setupRecyclerView()
        viewModel.setCharacters(currentEpisode.characters.idsParser())
        viewModel.characters.observe(viewLifecycleOwner) {
            setupCharacters(it)
        }
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        episodeDetailAdapter = EpisodeDetailAdapter(listener = this)
        binding.rvCharacters.apply {
            adapter = episodeDetailAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupCharacters(characters: List<Character>) {
        episodeDetailAdapter.characters.addAll(characters)
        episodeDetailAdapter.notifyDataSetChanged()
    }

    private fun setupEpisode() {
        with(binding) {
            tvTitle.text = currentEpisode.name
            episode.text = currentEpisode.episode
            airDate.text = currentEpisode.air_date
            created.text = currentEpisode.created
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

    override fun onClick(characterId: Int) {
        navigation.navigateToCharacterDetails(viewModel.getCharacter(characterId))
    }
}