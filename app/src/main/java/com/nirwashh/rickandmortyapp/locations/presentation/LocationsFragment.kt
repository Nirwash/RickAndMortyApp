package com.nirwashh.rickandmortyapp.locations.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirwashh.rickandmortyapp.R
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment(R.layout.fragment_locations) {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button3.setOnClickListener {
            navigation.navigateToLocationDetails()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

