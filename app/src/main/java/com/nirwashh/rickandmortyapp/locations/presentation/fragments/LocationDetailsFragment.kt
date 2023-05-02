package com.nirwashh.rickandmortyapp.locations.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.core.utils.idsParser
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationDetailsBinding
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.presentation.adapters.LocationDetailAdapter
import com.nirwashh.rickandmortyapp.locations.presentation.viewmodel.LocationViewModel
import com.nirwashh.rickandmortyapp.locations.presentation.viewmodel.LocationViewModelFactory
import javax.inject.Inject

class LocationDetailsFragment : Fragment(), LocationDetailAdapter.Listener {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation
    private lateinit var viewModel: LocationViewModel
    private lateinit var location: Location
    private lateinit var locationDetailAdapter: LocationDetailAdapter

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
        setupLocation()
        setupRecyclerView()
        viewModel.setCharacters(location.residents.idsParser())
        viewModel.characters.observe(viewLifecycleOwner) {
            setupCharacters(it)
        }
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        locationDetailAdapter = LocationDetailAdapter( listener = this)
        binding.rvCharacters.apply {
            adapter = locationDetailAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupLocation() {
        with(binding) {
            tvTitle.text = location.name
            created.text = location.created
            type.text = location.type
            dimension.text = location.dimension
        }
    }

    private fun setupCharacters(characters: List<Character>) {
        locationDetailAdapter.characters.addAll(characters)
        locationDetailAdapter.notifyDataSetChanged()
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

    override fun onClick(character: Character) {
        TODO("Not yet implemented")
    }
}