package com.nirwashh.rickandmortyapp.characters.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.CharacterLoadStateAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.CharactersAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModelFactory
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentCharactersBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFragment : Fragment(), RefreshCallback {
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
        setupSwipeToRefresh()
        binding.filter.setOnClickListener {
            CharacterFiltersFragment(viewModel)
                .show(childFragmentManager, "")
        }
    }


    private fun setupRecyclerView() {
        characterAdapter = CharactersAdapter()
        binding.rvCharacters.apply {
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadStateAdapter(characterAdapter),
                footer = CharacterLoadStateAdapter(characterAdapter)
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
            lifecycleScope.launch {
                updateUi()
            }
        }

    }


    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                updateUi()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private suspend fun updateUi() {
        characterAdapter.submitData(PagingData.empty())
        viewModel.charactersFlow.collectLatest(characterAdapter::submitData)
    }

    override fun invoke() {
        lifecycleScope.launch {
            updateUi()
        }
    }

}

