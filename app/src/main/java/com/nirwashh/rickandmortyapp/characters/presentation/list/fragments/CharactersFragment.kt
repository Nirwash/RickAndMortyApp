package com.nirwashh.rickandmortyapp.characters.presentation.list.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.nirwashh.rickandmortyapp.characters.presentation.list.adapters.CharacterLoadStateAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.list.adapters.CharactersAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels.CharactersViewModelFactory
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentCharactersBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFragment : Fragment(), CharacterFiltersFragment.RefreshCallback,
    CharactersAdapter.Listener {
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var navigation: Navigation
    private lateinit var viewModel: CharactersViewModel
    private lateinit var characterAdapter: CharactersAdapter

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadCharacters()
        setupSwipeToRefresh()
        observeFilters()
        binding.filter.setOnClickListener {
            CharacterFiltersFragment(viewModel)
                .show(childFragmentManager, "")
        }
    }

    private fun observeFilters() {
        lifecycle.coroutineScope.launch {
            viewModel.filters.collect {
                viewModel.load(
                    name = viewModel.filters.value.getValue("name"),
                    gender = viewModel.filters.value.getValue("gender"),
                    status = viewModel.filters.value.getValue("status"),
                    species = viewModel.filters.value.getValue("species"),
                    type = viewModel.filters.value.getValue("type")
                )
            }
        }
    }

    private fun loadCharacters() {
        lifecycleScope.launch {
            viewModel.load(null, null, null, null, null)
            viewModel.charactersFlow.collectLatest {
                characterAdapter.submitData(it)
            }
        }
        characterAdapter.addLoadStateListener {
            binding.rvCharacters.isVisible = it.refresh != LoadState.Loading
            binding.progressBar.isVisible = it.refresh == LoadState.Loading
        }
    }

    private fun setupRecyclerView() {
        characterAdapter = CharactersAdapter(this)
        binding.rvCharacters.apply {
            adapter = characterAdapter.withLoadStateFooter(
                footer = CharacterLoadStateAdapter(characterAdapter)
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.load(null, null, null, null, null)
            with(binding) {
                swipeRefreshLayout.isRefreshing = false
                rvCharacters.scrollToPosition(0)
            }
        }
    }

    override fun invoke(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ) {
        viewModel.load(name, status, gender, type, species)
        binding.rvCharacters.scrollToPosition(0)
    }

    override fun onClick(character: CharacterUi) {
        navigation.navigateToCharacterDetails(character)
    }

}

