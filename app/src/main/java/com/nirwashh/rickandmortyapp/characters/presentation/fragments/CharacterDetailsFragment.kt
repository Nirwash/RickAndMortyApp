package com.nirwashh.rickandmortyapp.characters.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
import com.nirwashh.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {
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
        with(binding) {
            btnBack.setOnClickListener { navigationBack() }
            tvTitle.text = character.name
        }

    }

    private fun setupRecyclerView() {
        characterDetailAdapter = CharacterDetailsAdapter(createViewItems(character))
        binding.rvCharacterDetails.apply {
            adapter = characterDetailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun createViewItems(character: Character): List<DetailsRecyclerViewItem> {
        val list = mutableListOf<DetailsRecyclerViewItem>()
        list.add(
            0, DetailsRecyclerViewItem.CharacterViewItem(
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
            1, DetailsRecyclerViewItem.LocationViewItem(
                name = character.location.name
            )
        )
        list.add(
            2, DetailsRecyclerViewItem.OriginViewItem(
                name = character.origin.name
            )
        )
        list.add(
            3,
            DetailsRecyclerViewItem.TitleViewItem("EPISODES")
        )
        character.episode.forEach {
            list.add(DetailsRecyclerViewItem.EpisodeViewItem(name = it, ""))
        }
        return list.toList()
    }


    private fun navigationBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigation.back()
            }
        }
    }

    companion object {
        private const val CHARACTER = "character"
        fun newInstance(character: Character): Fragment {
            val fragment = CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER, character)
                }
            }
            return fragment
        }
    }
}