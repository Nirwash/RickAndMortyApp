package com.nirwashh.rickandmortyapp.episodes.presentation.list.fragments

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
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentEpisodesBinding
import com.nirwashh.rickandmortyapp.episodes.presentation.list.adapters.EpisodeAdapter
import com.nirwashh.rickandmortyapp.episodes.presentation.list.adapters.EpisodeLoadStateAdapter
import com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels.EpisodeViewModelFactory
import com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels.EpisodesViewModel
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesFragment : Fragment(), EpisodeFiltersFragment.RefreshCallback,
    EpisodeAdapter.Listener {
    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var navigation: Navigation
    private lateinit var viewModel: EpisodesViewModel
    private lateinit var episodeAdapter: EpisodeAdapter

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadEpisodes()
        setupSwipeToRefresh()
        observeFilters()
        binding.filter.setOnClickListener {
            EpisodeFiltersFragment(viewModel)
                .show(childFragmentManager, "")
        }

    }

    private fun observeFilters() {
        lifecycle.coroutineScope.launch {
            viewModel.filters.collect {
                viewModel.load(
                    name = viewModel.filters.value.getValue("name"),
                    episode = viewModel.filters.value.getValue("episode")
                )
            }
        }
    }

    private fun loadEpisodes() {
        lifecycleScope.launch {
            viewModel.load(null, null)
            viewModel.episodesFlow.collectLatest {
                episodeAdapter.submitData(it)
            }
        }
        episodeAdapter.addLoadStateListener {
            binding.rvEpisodes.isVisible = it.refresh != LoadState.Loading
            binding.progressBar.isVisible = it.refresh == LoadState.Loading
        }
    }

    private fun setupRecyclerView() {
        episodeAdapter = EpisodeAdapter(this)
        binding.rvEpisodes.apply {
            adapter = episodeAdapter.withLoadStateFooter(
                footer = EpisodeLoadStateAdapter(episodeAdapter)
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.load(null, null)
            with(binding) {
                swipeRefreshLayout.isRefreshing = false
                rvEpisodes.scrollToPosition(0)
            }
        }
    }

    override fun invoke(
        name: String?,
        episode: String?
    ) {
        viewModel.load(name, episode)
        binding.rvEpisodes.scrollToPosition(0)
    }

    override fun onClick(episode: EpisodeUi) {
        navigation.navigateToEpisodeDetails(episode)
    }
}

