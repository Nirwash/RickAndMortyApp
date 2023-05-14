package com.nirwashh.rickandmortyapp.locations.presentation.list.fragments

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
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationsBinding
import com.nirwashh.rickandmortyapp.locations.presentation.list.adapters.LocationAdapter
import com.nirwashh.rickandmortyapp.locations.presentation.list.adapters.LocationLoadStateAdapter
import com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel.LocationViewModel
import com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel.LocationViewModelFactory
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsFragment : Fragment(), LocationAdapter.Listener,
    LocationFiltersFragment.RefreshCallback {
    private lateinit var binding: FragmentLocationsBinding
    private lateinit var navigation: Navigation
    private lateinit var viewModel: LocationViewModel
    private lateinit var locationAdapter: LocationAdapter

    @Inject
    lateinit var vmFactory: LocationViewModelFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        viewModel = ViewModelProvider(this, vmFactory)[LocationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadLocations()
        setupSwipeToRefresh()
        observeFilters()
        binding.filter.setOnClickListener {
            LocationFiltersFragment(viewModel).show(childFragmentManager, "")
        }

    }

    private fun observeFilters() {
        lifecycle.coroutineScope.launch {
            viewModel.filters.collect {
                viewModel.load(
                    name = viewModel.filters.value.getValue("name"),
                    type = viewModel.filters.value.getValue("type"),
                    dimension = viewModel.filters.value.getValue("dimension")
                )
            }
        }
    }

    private fun loadLocations() {
        lifecycleScope.launch {
            viewModel.load(null, null, null)
            viewModel.locationFlow.collectLatest {
                locationAdapter.submitData(it)
            }
        }
        locationAdapter.addLoadStateListener {
            binding.rvLocations.isVisible = it.refresh != LoadState.Loading
            binding.progressBar.isVisible = it.refresh == LoadState.Loading
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.load(null, null, null)
            with(binding) {
                swipeRefreshLayout.isRefreshing = false
                rvLocations.scrollToPosition(0)
            }
        }
    }

    private fun setupRecyclerView() {
        locationAdapter = LocationAdapter(this)
        binding.rvLocations.apply {
            adapter = locationAdapter.withLoadStateFooter(
                footer = LocationLoadStateAdapter(locationAdapter)
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onClick(location: LocationUi) {
        navigation.navigateToLocationDetails(location)
    }

    override fun invoke(
        name: String?,
        type: String?,
        dimension: String?
    ) {
        viewModel.load(name, type, dimension)
        binding.rvLocations.scrollToPosition(0)
    }
}

