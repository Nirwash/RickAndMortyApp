package com.nirwashh.rickandmortyapp.locations.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationDetailsBinding
import com.nirwashh.rickandmortyapp.locations.data.model.Location

class LocationDetailsFragment : Fragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = activity as Navigation
        location = arguments?.getParcelable(LOCATION)!!

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val LOCATION = "LOCATION"
        fun newInstance(location: Location): Fragment {
            val fragment = LocationDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LOCATION, location)
                }
            }
            return fragment
        }
    }
}