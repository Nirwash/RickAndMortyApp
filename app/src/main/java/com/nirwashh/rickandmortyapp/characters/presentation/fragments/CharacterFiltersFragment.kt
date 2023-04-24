package com.nirwashh.rickandmortyapp.characters.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.presentation.viewmodels.CharactersViewModel
import com.nirwashh.rickandmortyapp.core.utils.setWidthPercent
import com.nirwashh.rickandmortyapp.databinding.FragmentCharacterFilterBinding

class CharacterFiltersFragment(private val viewModel: CharactersViewModel) : DialogFragment() {
    private lateinit var binding: FragmentCharacterFilterBinding
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
        binding = FragmentCharacterFilterBinding.inflate(inflater, container, false)
        return binding.root
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
            species.setText(filters.species)
            checkGender(filters.gender)
            checkStatus(filters.status)
        }
    }

    private fun checkGender(gender: String) {
        when (gender) {
            MALE -> binding.chipMale.isChecked = true
            FEMALE -> binding.chipFemale.isChecked = true
            GENDERLESS -> binding.chipGenderless.isChecked = true
            UNKNOWN -> binding.chipUnknownGender.isChecked = true
        }
    }

    private fun checkStatus(status: String) {
        when (status) {
            ALIVE -> binding.chipAlive.isChecked = true
            DEAD -> binding.chipDead.isChecked = true
            UNKNOWN -> binding.chipUnknownStatus.isChecked = true
        }
    }

    private fun choiceGender(): String {
        return if (binding.chipMale.isChecked)
            MALE
        else if (binding.chipFemale.isChecked)
            FEMALE
        else if (binding.chipGenderless.isChecked)
            GENDERLESS
        else if (binding.chipUnknownGender.isChecked)
            UNKNOWN
        else
            ""
    }

    private fun choiceStatus(): String {
        return if (binding.chipAlive.isChecked)
            ALIVE
        else if (binding.chipDead.isChecked)
            DEAD
        else if (binding.chipUnknownStatus.isChecked)
            UNKNOWN
        else
            ""
    }

    private fun applyFilters() {
        val name = binding.search.text.toString()
        val type = binding.type.text.toString()
        val species = binding.species.text.toString()
        val gender = choiceGender()
        val status = choiceStatus()
        viewModel.updateFilters(CharacterFilters(name, status, species, type, gender))
    }

    private fun updateAndClose() {
        viewModel.update()
        refresh()
        dismiss()
    }

    companion object {
        private const val MALE = "male"
        private const val FEMALE = "female"
        private const val GENDERLESS = "genderless"
        private const val ALIVE = "alive"
        private const val DEAD = "dead"
        private const val UNKNOWN = "unknown"
    }
}

interface RefreshCallback {
    operator fun invoke()
}