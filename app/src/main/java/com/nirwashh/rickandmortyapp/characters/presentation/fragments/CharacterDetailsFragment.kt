package com.nirwashh.rickandmortyapp.characters.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.CharacterDetailsAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.DetailsRecyclerViewItem
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModelFactory
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.core.utils.idsParser
import com.nirwashh.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import javax.inject.Inject

class CharacterDetailsFragment : Fragment(), CharacterDetailsAdapter.Listener {
    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var navigation: Navigation
    private lateinit var viewModel: CharactersViewModel
    private lateinit var character: Character
    private lateinit var characterDetailAdapter: CharacterDetailsAdapter

    @Inject
    lateinit var vmFactory: CharactersViewModelFactory


    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        viewModel = ViewModelProvider(this, vmFactory)[CharactersViewModel::class.java]
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
        viewModel.setEpisodes(character.episode.idsParser())
        viewModel.setLocations(character.location, character.origin)
        viewModel.episodes.observe(viewLifecycleOwner) {
            setupEpisodesViewType(it)
        }
        with(binding) {
            btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
            tvTitle.text = character.name
        }


    }

    private fun setupRecyclerView() {
        characterDetailAdapter = CharacterDetailsAdapter(createViewItems(character), this)
        binding.rvCharacterDetails.apply {
            adapter = characterDetailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun createViewItems(character: Character): MutableList<DetailsRecyclerViewItem> {
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
        return list
    }

    private fun setupEpisodesViewType(episode: List<Episode>) {
        episode.forEach {
            characterDetailAdapter.viewItems.add(
                DetailsRecyclerViewItem.EpisodeViewItem(
                    name = it.name,
                    episode = it.episode,
                    id = it.id
                )
            )
        }
        characterDetailAdapter.notifyDataSetChanged()
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
                navigation.navigateToEpisodeDetails(viewModel.getEpisode(viewItem.id))

            is DetailsRecyclerViewItem.LocationViewItem -> {
                if (viewItem.name != "unknown") {
                    navigation.navigateToLocationDetails(viewModel.getLocation(viewItem.name))
                }
            }

            is DetailsRecyclerViewItem.OriginViewItem -> {
                if (viewItem.name != "unknown") {
                    navigation.navigateToLocationDetails(viewModel.getLocation(viewItem.name))
                }
            }
        }
    }
}