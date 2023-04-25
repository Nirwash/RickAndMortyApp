package com.nirwashh.rickandmortyapp.locations.presentation

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
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationsBinding
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.presentation.adapters.LocationAdapter
import com.nirwashh.rickandmortyapp.locations.presentation.adapters.LocationLoadStateAdapter
import com.nirwashh.rickandmortyapp.locations.presentation.viewmodel.LocationViewModel
import com.nirwashh.rickandmortyapp.locations.presentation.viewmodel.LocationViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsFragment : Fragment(), LocationAdapter.Listener {
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
        setupSwipeToRefresh()

    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                updateUi()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        locationAdapter = LocationAdapter(this)
        binding.rvLocations.apply {
            adapter = locationAdapter.withLoadStateHeaderAndFooter(
                header = LocationLoadStateAdapter(locationAdapter),
                footer = LocationLoadStateAdapter(locationAdapter)
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
            lifecycleScope.launch {
                updateUi()
            }
        }
    }

    private suspend fun updateUi() {
        locationAdapter.submitData(PagingData.empty())
        viewModel.locationFlow.collectLatest(locationAdapter::submitData)
    }


    override fun onClick(location: Location) {
        navigation.navigateToLocationDetails()
    }
}

