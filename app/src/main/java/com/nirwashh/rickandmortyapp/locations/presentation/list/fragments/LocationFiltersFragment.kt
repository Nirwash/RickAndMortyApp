package com.nirwashh.rickandmortyapp.locations.presentation.list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.core.utils.setWidthPercent
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationFilterBinding
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
        operator fun invoke(
            name: String?,
            type: String?,
            dimension: String?
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        init()
        with(binding) {
            clearButton.setOnClickListener {
                clearFilters()
            }
            applyButton.setOnClickListener {
                applyFilters()
            }
        }
    }

    private fun clearFilters() {
        refresh(null, null, null)
        dismiss()
    }


    private fun init() {
        with(binding) {
            search.setText(viewModel.filters.value.getValue("name"))
            type.setText(viewModel.filters.value.getValue("type"))
            dimension.setText(viewModel.filters.value.getValue("dimension"))
        }
    }

    private fun applyFilters() {
        val name = if (binding.search.text.toString() == "")
            null
        else
            binding.search.text.toString()
        val type = if (binding.type.text.toString() == "")
            null
        else
            binding.type.text.toString()
        val dimension = if (binding.dimension.text.toString() == "")
            null
        else
            binding.dimension.text.toString()
        refresh(name, type, dimension)
        dismiss()
    }

}



