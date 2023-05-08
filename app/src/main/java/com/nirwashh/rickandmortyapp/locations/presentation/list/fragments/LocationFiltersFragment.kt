package com.nirwashh.rickandmortyapp.locations.presentation.list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.core.utils.setWidthPercent
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationFilterBinding
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel.LocationViewModel

class LocationFiltersFragment(private val viewModel: LocationViewModel) : DialogFragment() {
    private lateinit var binding: FragmentLocationFilterBinding
    private lateinit var refresh: RefreshCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh = parentFragment as RefreshCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    interface RefreshCallback {
        operator fun invoke()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        init()
        with(binding) {
            clearButton.setOnClickListener {
                viewModel.clearFilters()
                updateAndClose()
            }
            applyButton.setOnClickListener {
                applyFilters()
                updateAndClose()
            }
        }
    }


    private fun init() {
        val filters = viewModel.filterState.value
        with(binding) {
            search.setText(filters.name)
            type.setText(filters.type)
            dimension.setText(filters.dimension)
        }
    }

    private fun applyFilters() {
        val name = binding.search.text.toString()
        val type = binding.type.text.toString()
        val dimension = binding.dimension.text.toString()
        viewModel.updateFilters(LocationFilters(name, type, dimension))
    }

    private fun updateAndClose() {
        viewModel.update()
        refresh()
        dismiss()
    }
}



