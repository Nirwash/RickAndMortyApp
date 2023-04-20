package com.nirwashh.rickandmortyapp.characters.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nirwashh.rickandmortyapp.characters.presentation.adapters.CharactersAdapter
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModelFactory
import com.nirwashh.rickandmortyapp.core.App
import com.nirwashh.rickandmortyapp.core.presentation.Navigation
import com.nirwashh.rickandmortyapp.core.util.Resource
import com.nirwashh.rickandmortyapp.databinding.FragmentCharactersBinding
import javax.inject.Inject

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var navigation: Navigation
    private lateinit var characterAdapter: CharactersAdapter
    private lateinit var viewModel: CharactersViewModel
    var isLoading = false

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
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initSearchView()
        viewModel.characters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        characterAdapter.differ.submitList(it.results.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
        viewModel.isFocusedSearchView.observe(viewLifecycleOwner) {
            if (it)
                binding.tvTitle.visibility = View.INVISIBLE
            else
                binding.tvTitle.visibility = View.VISIBLE

        }
    }

    private fun setupRecyclerView() {
        characterAdapter = CharactersAdapter()

        binding.rvCharacters.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initSearchView() {
        with(binding) {
            with(searchView) {
                setOnQueryTextFocusChangeListener { _, boolean ->
                    viewModel.isFocusedSearchView.value = boolean
                }
                setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.isNullOrEmpty())
                            viewModel.searchQueryListener(query)
                        else
                            viewModel.searchQueryListener("")
                        viewModel.fetchCharacters()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (!newText.isNullOrEmpty())
                            viewModel.searchQueryListener(newText)
                        else
                            viewModel.searchQueryListener("")
                        viewModel.fetchCharacters()
                        return false
                    }
                })
            }
        }

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

