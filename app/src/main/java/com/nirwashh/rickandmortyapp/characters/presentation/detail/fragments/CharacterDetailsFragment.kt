package com.nirwashh.rickandmortyapp.characters.presentation.detail.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.CharacterDetailsAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailsRecyclerViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel.CharacterDetailViewModelFactory
import com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel.CharacterDetailViewModelJava
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.core.utils.idParser
import com.nirwashh.rickandmortyapp.core.utils.idsParser
import com.nirwashh.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import javax.inject.Inject

class CharacterDetailsFragment : Fragment(), CharacterDetailsAdapter.Listener {
    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var navigation: Navigation
    private lateinit var viewModel: CharacterDetailViewModelJava
    private lateinit var character: Character
    private lateinit var characterDetailAdapter: CharacterDetailsAdapter
    private lateinit var episodes: List<Episode>
    private lateinit var location: Location
    private lateinit var origin: Location


    @Inject
    lateinit var vmFactory: CharacterDetailViewModelFactory


    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        viewModel = ViewModelProvider(this, vmFactory)[CharacterDetailViewModelJava::class.java]
        character = arguments?.getParcelable(CHARACTER)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setsLiveData()
        observeLiveData()
        with(binding) {
            btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
            tvTitle.text = character.name
        }


    }

    private fun observeLiveData() {
        viewModel.episodesLiveData.observe(viewLifecycleOwner) {
            episodes = it
            updateUi()
        }
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            location = it
        }
        viewModel.originLiveData.observe(viewLifecycleOwner) {
            origin = it
        }
    }

    private fun updateUi() {
        characterDetailAdapter.viewItems.clear()
        characterDetailAdapter.viewItems.addAll(createViewItems(character))
        characterDetailAdapter.notifyDataSetChanged()
    }

    private fun setsLiveData() {
        viewModel.setEpisodesLiveData(character.episode.idsParser())
        viewModel.setLocationLiveData(character.location.url.idParser())
        viewModel.setOriginLiveData(character.origin.url.idParser())
    }

    private fun setupRecyclerView() {
        characterDetailAdapter = CharacterDetailsAdapter(listener = this)
        binding.rvCharacterDetails.apply {
            adapter = characterDetailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun createViewItems(character: Character): List<DetailsRecyclerViewItem> {
        val list = mutableListOf<DetailsRecyclerViewItem>()
        list.add(
            DetailsRecyclerViewItem.CharacterViewItem(
                created = character.created,
                gender = character.gender,
                image = character.image,
                name = character.name,
                species = character.species,
                status = character.status,
                type = character.type
            )
        )
        list.add(
            DetailsRecyclerViewItem.TitleViewItem(LOCATION)
        )
        list.add(
            DetailsRecyclerViewItem.LocationViewItem(
                name = character.location.name,
                id = character.location.id
            )
        )
        list.add(
            DetailsRecyclerViewItem.TitleViewItem(ORIGIN)
        )
        list.add(
            DetailsRecyclerViewItem.OriginViewItem(
                name = character.origin.name,
                id = character.location.id
            )
        )
        list.add(
            DetailsRecyclerViewItem.TitleViewItem(EPISODES)
        )
        episodes.forEach {
            list.add(
                DetailsRecyclerViewItem.EpisodeViewItem(
                    name = it.name,
                    episode = it.episode,
                    id = it.id
                )
            )
        }
        return list
    }

    companion object {
        private const val CHARACTER = "character"
        private const val LOCATION = "LOCATION: "
        private const val ORIGIN = "ORIGIN: "
        private const val EPISODES = "EPISODES: "
        fun newInstance(character: Character): Fragment {
            val fragment = CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER, character)
                }
            }
            return fragment
        }
    }

    override fun <T> onClick(viewItem: T) {
        when (viewItem) {
            is DetailsRecyclerViewItem.EpisodeViewItem ->
                episodes.find { it.id == viewItem.id }
                    ?.let { navigation.navigateToEpisodeDetails(it) }

            is DetailsRecyclerViewItem.LocationViewItem -> {
                if (viewItem.name != "unknown") {
                    navigation.navigateToLocationDetails(location)
                }
            }

            is DetailsRecyclerViewItem.OriginViewItem -> {
                if (viewItem.name != "unknown") {
                    navigation.navigateToLocationDetails(origin)
                }
            }
        }
    }
}